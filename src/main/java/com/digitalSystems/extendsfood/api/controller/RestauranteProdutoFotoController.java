package com.digitalSystems.extendsfood.api.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.digitalSystems.extendsfood.api.assembler.FotoProdutoModelAssembler;
import com.digitalSystems.extendsfood.api.model.FotoProdutoModel;
import com.digitalSystems.extendsfood.api.model.inputEntidade.FotoProdutoInput;
import com.digitalSystems.extendsfood.domain.model.FotoProduto;
import com.digitalSystems.extendsfood.domain.model.Produto;
import com.digitalSystems.extendsfood.domain.service.CatalogoFotoProdutoService;
import com.digitalSystems.extendsfood.domain.service.FotoStorageService;
import com.digitalSystems.extendsfood.domain.service.FotoStorageService.FotoRecuperada;
import com.digitalSystems.extendsfood.domain.service.ProdutoService;

@RestController
@RequestMapping("restaurante/{restauranteId}/categorias/{categoriaId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private CatalogoFotoProdutoService catalogoFotoProduto;

	@Autowired
	private FotoStorageService fotoStorage;

	@Autowired
	private FotoProdutoModelAssembler fotoProdutoModelAssembler;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public FotoProdutoModel buscar(@PathVariable Long restauranteId, @PathVariable Long categoriaId,
			@PathVariable Long produtoId) {

		FotoProduto fotoProduto = catalogoFotoProduto.buscarOuFalhar(categoriaId, restauranteId, produtoId);

		return fotoProdutoModelAssembler.toModel(fotoProduto);
	}

	@GetMapping
	public ResponseEntity<?> servirFoto(@PathVariable Long restauranteId,
			@PathVariable Long categoriaId, @PathVariable Long produtoId,
			@RequestHeader(name = "accept") String acceptHeader) throws HttpMediaTypeNotAcceptableException {
		
		FotoProduto fotoProduto = catalogoFotoProduto.buscarOuFalhar(categoriaId, restauranteId, produtoId);

		// Pega o MediaType da foto salva
		MediaType mediaTypeFoto = MediaType.parseMediaType(fotoProduto.getContentType());

		// pega a lista de MediaType que o usuário informou no cabeçalho
		List<MediaType> mediaTypesAceitas = MediaType.parseMediaTypes(acceptHeader);

		// compara o MediaType da foto recuperada com a da passada por parâmetro
		// verifica se o MediaType da foto recuperada e compativel com os MediaType
		// passados como parâmetro
		verificarCompatibilidadeMediaType(mediaTypeFoto, mediaTypesAceitas);

		FotoRecuperada fotoRecuperada = fotoStorage.recuperar(fotoProduto.getNomeArquivo());

		if (fotoRecuperada.temUrl()) {
			
			//HttpStatus.FOUND indica que o recurso existe, mas precisa seguir uma URL até ele
			//HttpHeaders.LOCATION indica a URL que o usuário dele serguir (Onde se encontra a foto)
			return ResponseEntity.status(HttpStatus.FOUND)
					.header(HttpHeaders.LOCATION, fotoRecuperada.getUrl())
					.build();
		} else {
			return ResponseEntity.ok().contentType(mediaTypeFoto)
					.body(new InputStreamResource(fotoRecuperada.getInputStream()));
		}
	}
	
	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public FotoProdutoModel atualizarFoto(@PathVariable Long restauranteId, @PathVariable Long categoriaId,
			@PathVariable Long produtoId, @Valid FotoProdutoInput fotoProdutoInput) throws IOException {

		Produto produto = produtoService.buscarOuFalhar(restauranteId, categoriaId, produtoId);

		MultipartFile arquivo = fotoProdutoInput.getArquivo();

		FotoProduto foto = new FotoProduto();
		foto.setProduto(produto);
		foto.setDescricao(fotoProdutoInput.getDescricao());
		foto.setContentType(arquivo.getContentType());
		foto.setTamanho(arquivo.getSize());
		foto.setNomeArquivo(arquivo.getOriginalFilename());

		FotoProduto fotoSalva = catalogoFotoProduto.salvar(foto, arquivo.getInputStream());

		return fotoProdutoModelAssembler.toModel(fotoSalva);
	}
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removerFotoProduto(@PathVariable Long restauranteId, @PathVariable Long categoriaId,
			@PathVariable Long produtoId) {

		catalogoFotoProduto.remover(categoriaId, restauranteId, produtoId);
	}

	private void verificarCompatibilidadeMediaType(MediaType mediaTypeFoto, List<MediaType> mediaTypesAceitas)
			throws HttpMediaTypeNotAcceptableException {

		boolean compativel = mediaTypesAceitas.stream()
				.anyMatch(mediaTypeAceita -> mediaTypeAceita.isCompatibleWith(mediaTypeFoto));

		if (!compativel) {
			throw new HttpMediaTypeNotAcceptableException(mediaTypesAceitas);
		}
	}

}
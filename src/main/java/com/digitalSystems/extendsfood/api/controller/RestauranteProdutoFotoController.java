package com.digitalSystems.extendsfood.api.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.digitalSystems.extendsfood.api.assembler.FotoProdutoModelAssembler;
import com.digitalSystems.extendsfood.api.model.FotoProdutoModel;
import com.digitalSystems.extendsfood.api.model.inputEntidade.FotoProdutoInput;
import com.digitalSystems.extendsfood.domain.model.FotoProduto;
import com.digitalSystems.extendsfood.domain.model.Produto;
import com.digitalSystems.extendsfood.domain.service.CatalogoFotoProdutoService;
import com.digitalSystems.extendsfood.domain.service.ProdutoService;

@RestController
@RequestMapping("restaurante/{restauranteId}/categorias/{categoriaId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private CatalogoFotoProdutoService catalogoFotoProduto;

	@Autowired
	private FotoProdutoModelAssembler fotoProdutoModelAssembler;

	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public FotoProdutoModel atualizarFoto(@PathVariable Long restauranteId, @PathVariable Long categoriaId, @PathVariable Long produtoId,
			@Valid FotoProdutoInput fotoProdutoInput) throws IOException {
		
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
	
	@GetMapping
	public FotoProdutoModel buscar(@PathVariable Long categoriaId, @PathVariable Long produtoId) {
	    FotoProduto fotoProduto = catalogoFotoProduto.buscarOuFalhar(categoriaId, produtoId);
	    
	    return fotoProdutoModelAssembler.toModel(fotoProduto);
	}

}
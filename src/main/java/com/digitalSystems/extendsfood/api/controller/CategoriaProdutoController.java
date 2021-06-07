package com.digitalSystems.extendsfood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.digitalSystems.extendsfood.api.assembler.CategoriaProdutoModelAssembler;
import com.digitalSystems.extendsfood.api.disassempler.CategoriaProdutoInputDisassembler;
import com.digitalSystems.extendsfood.api.model.CategoriaProdutoModel;
import com.digitalSystems.extendsfood.api.model.CategoriaProdutoResumoModel;
import com.digitalSystems.extendsfood.api.model.inputEntidade.CategoriaProdutoInput;
import com.digitalSystems.extendsfood.api.openapi.controller.CategoriaProdutoControllerOpenApi;
import com.digitalSystems.extendsfood.domain.model.CategoriaProduto;
import com.digitalSystems.extendsfood.domain.service.CategoriaProdutoService;

@RestController
@RequestMapping(path = "/restaurantes/{restauranteId}/categorias", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoriaProdutoController implements CategoriaProdutoControllerOpenApi{

	@Autowired
	private CategoriaProdutoService categoriaProdutoService;
	
	@Autowired
	private CategoriaProdutoModelAssembler categoriaProdutoAssembler;
	
	@Autowired
	private CategoriaProdutoInputDisassembler categoriaProdutoDisassembler;

	@GetMapping
	public List<CategoriaProdutoResumoModel> listar(@PathVariable Long restauranteId) {
		return categoriaProdutoAssembler.toCollectionModel(
				categoriaProdutoService.buscarCategoriasRestarante(restauranteId));
	}

	@GetMapping("/{categoriaProdutoId}")
	public CategoriaProdutoModel buscar(@PathVariable Long restauranteId, @PathVariable Long categoriaProdutoId) {
		
		CategoriaProduto categoriaProduto = categoriaProdutoService.buscarOuFalhar(restauranteId, categoriaProdutoId);
		
		return categoriaProdutoAssembler.toModel(categoriaProduto);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CategoriaProdutoResumoModel adicionar(@PathVariable Long restauranteId, 
			@RequestBody @Valid CategoriaProdutoInput categoriaProdutoInput) {
		
		CategoriaProduto categoriaProduto = categoriaProdutoDisassembler.toDomainObject(categoriaProdutoInput);
		
		return categoriaProdutoAssembler.toModelResumo(categoriaProdutoService.salvar(categoriaProduto, restauranteId));
	}

	@PutMapping("/{categoriaProdutoId}")
	public CategoriaProdutoResumoModel atualizar(@PathVariable Long restauranteId, @PathVariable Long categoriaProdutoId,
			@RequestBody @Valid CategoriaProdutoInput categoriaProdutoInput) {

		CategoriaProduto categoriaProdutoAtual = categoriaProdutoService.buscarOuFalhar(restauranteId, categoriaProdutoId);

		categoriaProdutoDisassembler.copyToDomainObject(categoriaProdutoInput, categoriaProdutoAtual);

		return  categoriaProdutoAssembler.toModelResumo(categoriaProdutoService.salvar(categoriaProdutoAtual, restauranteId));

	}

	@DeleteMapping("/{categoriaProdutoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long restauranteId, @PathVariable Long categoriaProdutoId) {
		categoriaProdutoService.excluir(restauranteId, categoriaProdutoId);			
	}
}

package com.digitalSystems.extendsfood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.digitalSystems.extendsfood.api.assembler.ProdutoModelAssembler;
import com.digitalSystems.extendsfood.api.disassempler.ProdutoInputDisassembler;
import com.digitalSystems.extendsfood.api.model.ProdutoModel;
import com.digitalSystems.extendsfood.api.model.inputEntidade.ProdutoInput;
import com.digitalSystems.extendsfood.api.openapi.controller.RestauranteCategoriaProdutoControllerOpenApi;
import com.digitalSystems.extendsfood.domain.model.Produto;
import com.digitalSystems.extendsfood.domain.service.ProdutoService;

@RestController
@RequestMapping(path = "restaurante/{restauranteId}/categorias/{categoriaId}/produtos", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteCategoriaProdutoController implements RestauranteCategoriaProdutoControllerOpenApi{

	
	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private ProdutoInputDisassembler produtoDisassembler;
	
	@Autowired
	private ProdutoModelAssembler produtoAssembler;

	@GetMapping
	public List<ProdutoModel> listar(@PathVariable Long restauranteId, @PathVariable Long categoriaId){
		
		List<Produto> produtos = produtoService.buscarTodosProdutosCategoriaRestaurante(restauranteId, categoriaId);
		
		return produtoAssembler.toCollectionModel(produtos);
	}
	
	@GetMapping("/{produtoId}")
	public ProdutoModel buscarProdutoCategoriaRestaurante(@PathVariable Long restauranteId, 
			@PathVariable Long categoriaId, @PathVariable Long produtoId) {
		
		Produto produto = produtoService.buscarOuFalhar(restauranteId, categoriaId, produtoId);
		
		return produtoAssembler.toModel(produto);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoModel adicionar(@RequestBody @Valid ProdutoInput produtoInput, @PathVariable Long restauranteId,
			@PathVariable Long categoriaId) {

		Produto produto = produtoDisassembler.toDomainObject(produtoInput);

		return produtoAssembler.toModel(produtoService.salvar(produto, categoriaId, restauranteId));

	}
	
	@PutMapping("/{produtoId}")
	public ProdutoModel atualizar(@RequestBody @Valid ProdutoInput produtoInput,  @PathVariable Long restauranteId, 
			@PathVariable Long categoriaId,  @PathVariable Long produtoId) {
		
		Produto produtoAtual = produtoService.buscarOuFalhar(restauranteId, categoriaId, produtoId);
		
		produtoDisassembler.copyToDomainObject(produtoInput, produtoAtual);
		
		return produtoAssembler.toModel(produtoService.salvar(produtoAtual, categoriaId, restauranteId));
		
	}
}

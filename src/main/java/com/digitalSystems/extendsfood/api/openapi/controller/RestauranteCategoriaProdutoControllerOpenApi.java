package com.digitalSystems.extendsfood.api.openapi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.digitalSystems.extendsfood.api.exceptionHandler.Problem;
import com.digitalSystems.extendsfood.api.model.ProdutoModel;
import com.digitalSystems.extendsfood.api.model.inputEntidade.ProdutoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Produtos")
public interface RestauranteCategoriaProdutoControllerOpenApi {

	@ApiOperation("Lista todos os Produtos de uma Categoria")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do Restaurante ou da Categoria inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Restaurante ou da Categoria não encontrado", response = Problem.class)
	})
	Page<ProdutoModel> listar(
			@ApiParam(value = "ID do restaurante", example = "1", required = true)
			Long restauranteId, 
			
			@ApiParam(value = "ID da Categoria", example = "1", required = true)
			Long categoriaId,
			
			Pageable pageable);
	
	@ApiOperation("Busca um Produto por ID de uma Categoria")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do Restaurante, Categoria ou do Produto inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Restaurante, Categoria ou do Produto não encontrado", response = Problem.class)
	})
	ProdutoModel buscarProdutoCategoriaRestaurante(
			@ApiParam(value = "ID do restaurante", example = "1", required = true)
			Long restauranteId, 
			
			@ApiParam(value = "ID da Categoria", example = "1", required = true)
			Long categoriaId, 
			
			@ApiParam(value = "ID do Produto", example = "1", required = true)
			Long produtoId);
	
	@ApiOperation("Cadastra um Produto")
	@ApiResponses({
		@ApiResponse(code = 404, message = "Restaurante ou Categoria não encontrado", response = Problem.class),
		@ApiResponse(code = 201, message = "Produto cadastrado")
	})
	ProdutoModel adicionar(
			@ApiParam(name = "corpo", value = "Representação de um novo Produto", required = true)
			ProdutoInput produtoInput, 
			
			@ApiParam(value = "ID do restaurante", example = "1", required = true)
			Long restauranteId,
			
			@ApiParam(value = "ID da Categoria", example = "1", required = true)
			Long categoriaId);
	
	@ApiOperation("Atualiza um Produto")
	@ApiResponses({
		@ApiResponse(code = 404, message = "Restaurante ou Categoria não encontrado", response = Problem.class),
		@ApiResponse(code = 201, message = "Produto adicionado na categoria")
	})
	public ProdutoModel atualizar(
			@ApiParam(name = "corpo", value = "Representação de Produto com novos dados", required = true)
			ProdutoInput produtoInput,
			
			@ApiParam(value = "ID do restaurante", example = "1", required = true)
			Long restauranteId, 
			
			@ApiParam(value = "ID da Categoria", example = "1", required = true)
			Long categoriaId,  
			
			@ApiParam(value = "ID do Produto", example = "1", required = true)
			Long produtoId);
}

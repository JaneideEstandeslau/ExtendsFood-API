package com.digitalSystems.extendsfood.api.openapi.controller;

import java.util.List;

import com.digitalSystems.extendsfood.api.exceptionHandler.Problem;
import com.digitalSystems.extendsfood.api.model.CategoriaProdutoModel;
import com.digitalSystems.extendsfood.api.model.CategoriaProdutoResumoModel;
import com.digitalSystems.extendsfood.api.model.inputEntidade.CategoriaProdutoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Categorias dos Produtos")
public interface CategoriaProdutoControllerOpenApi {
	
	@ApiOperation("Lista todas as Categorias dos Produtos")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do Restaurante inválido", response = Problem.class)
	})
	List<CategoriaProdutoResumoModel> listar(
			
			@ApiParam(value = "ID do Restaurante", example = "1")
			Long restauranteId);
	
	@ApiOperation("Busca uma Categoria do Produto no Restaurante por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do Restaurante ou da Categoria inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Restaurante ou Categoria não encontrado", response = Problem.class)
	})
	CategoriaProdutoModel buscar(
			
			@ApiParam(value = "ID do Restaurante", example = "1", required = true)
			Long restauranteId, 
			
			@ApiParam(value = "ID da Categoria do Produto", example = "1", required = true)
			Long categoriaProdutoId);

	@ApiOperation("Cadastra uma Categoria de Produto em um Restaurante ")
	@ApiResponses({
		@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class),
		@ApiResponse(code = 201, message = "Categoria Cadastrada")
	})
	CategoriaProdutoResumoModel adicionar(
			
			@ApiParam(value = "ID do Restaurante", example = "1", required = true)
			Long restauranteId, 
			
			@ApiParam(name = "corpo", value = "Representação de uma nova Categoria de Produro")
			CategoriaProdutoInput categoriaProdutoInput);

	
	@ApiResponses({
		@ApiResponse(code = 404, message = "Restaurante ou Categoria não encontrado", response = Problem.class),
		@ApiResponse(code = 200, message = "Categoria Atualizada")
	})
	@ApiOperation("Atualiza uma Categoria de Produto de um Restaurante por ID")
	CategoriaProdutoResumoModel atualizar(
			
			@ApiParam(value = "ID do Restaurante", example = "1", required = true)
			Long restauranteId, 
			
			@ApiParam(value = "ID da Categoria do Produto", example = "1", required = true)
			Long categoriaProdutoId, 
			
			@ApiParam(name = "corpo", value = "Representação de uma Categoria de Produro com novos dados")
			CategoriaProdutoInput categoriaProdutoInput);

	
	@ApiOperation("Remove uma Categoria de Produto de um Restaurante por ID")
	@ApiResponses({
		@ApiResponse(code = 404, message = "Restaurante ou Categoria não encontrado", response = Problem.class),
		@ApiResponse(code = 409, message = "Categoria em uso", response = Problem.class),
		@ApiResponse(code = 204, message = "Categoria Excuida")
	})
	void remover(
			
			@ApiParam(value = "ID do Restaurante", example = "1", required = true)
			Long restauranteId, 
			
			@ApiParam(value = "ID da Categoria do Produto", example = "1", required = true)
			Long categoriaProdutoId);

}

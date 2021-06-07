package com.digitalSystems.extendsfood.api.openapi.controller;

import java.util.List;

import com.digitalSystems.extendsfood.api.exceptionHandler.Problem;
import com.digitalSystems.extendsfood.api.model.CozinhaModel;
import com.digitalSystems.extendsfood.api.model.inputEntidade.CozinhaInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Cozinha")
public interface CozinhaControllerOpenApi {

	@ApiOperation("Lista as Cozinhas")
	public List<CozinhaModel> listar();

	@ApiOperation("Busca uma Cozinha por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID da Cozinha inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)
	})
	public CozinhaModel buscar(
			@ApiParam(value = "ID de uma Cozinha", example = "1", required = true)
			Long cozinhaId);

	@ApiOperation("Cadastra uma cidade")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Cidade cadastrada"),
	})
	public CozinhaModel adicionar(
			@ApiParam(name = "corpo", value = "Representação de uma nova Cozinha", required = true)
			CozinhaInput cozinhaInput);

	@ApiOperation("Atualiza uma Cozinha por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Cozinha atualizada"),
		@ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)
	})
	public CozinhaModel atualizar(
			@ApiParam(value = "ID de uma Cozinha", example = "1", required = true)
			Long cozinhaId, 
			
			@ApiParam(name = "corpo", value = "Representação de uma Cozinha com novos dados", required = true)
			CozinhaInput cozinhaInput);

	@ApiOperation("Exclui uma Cozinha por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Cozinha excluída"),
		@ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)
	})
	public void remover(
			@ApiParam(value = "ID de uma Cozinha", example = "1", required = true)
			Long cozinhaId);
}

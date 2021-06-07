package com.digitalSystems.extendsfood.api.openapi.controller;

import java.util.List;

import com.digitalSystems.extendsfood.api.exceptionHandler.Problem;
import com.digitalSystems.extendsfood.api.model.EstadoModel;
import com.digitalSystems.extendsfood.api.model.inputEntidade.EstadoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Estados")
public interface EstadoControllerOpenApi {

	@ApiOperation("Lista os Estados")
	List<EstadoModel> listar();

	@ApiOperation("Busca um Estado por ID")
	@ApiResponses({ 
		@ApiResponse(code = 400, message = "ID do Estado inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Estado não encontrada", response = Problem.class) 
	})
	EstadoModel buscar(
		@ApiParam(value = "ID de um Estado", example = "1", required = true) 
		Long estadoId);

	@ApiOperation("Cadastra um Estado")
	@ApiResponses({ 
		@ApiResponse(code = 201, message = "Estado cadastrado")
	})
	EstadoModel adicionar(
		@ApiParam(name = "corpo", value = "Representação de um novo Estado", required = true) 
		EstadoInput estadoInput);

	@ApiOperation("Atualiza um Estado por ID")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "Estado atualizado"),
		@ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class) 
	})
	EstadoModel atualizar(
			@ApiParam(value = "ID de um Estado", example = "1", required = true) 
			Long estadoId,

			@ApiParam(name = "corpo", value = "Representação de um Estado com novos dados", required = true) 
			EstadoInput estadoInput);

	@ApiOperation("Exclui um Estado por ID")
	@ApiResponses({ 
		@ApiResponse(code = 204, message = "Estado excluído"),			
		@ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class),
		@ApiResponse(code = 400, message = "ID do Estado inválido", response = Problem.class) 
	})
	void remover(
			@ApiParam(value = "ID de um Estado", example = "1", required = true) 
			Long estadoId);
}

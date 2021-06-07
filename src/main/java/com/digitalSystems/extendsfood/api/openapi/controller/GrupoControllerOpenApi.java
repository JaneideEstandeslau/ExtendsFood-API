package com.digitalSystems.extendsfood.api.openapi.controller;

import java.util.List;

import com.digitalSystems.extendsfood.api.exceptionHandler.Problem;
import com.digitalSystems.extendsfood.api.model.GrupoModel;
import com.digitalSystems.extendsfood.api.model.inputEntidade.GrupoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Grupos")
public interface GrupoControllerOpenApi {

	@ApiOperation("Lista os Grupos")
	List<GrupoModel> listar();

	@ApiOperation("Lista um Grupo por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
	})
	GrupoModel buscar(
			@ApiParam(value = "ID de um Grupo", example = "1", required = true)
			Long grupoId);

	@ApiOperation("Lista um Grupo por ID")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Grupo Cadastrado")
	})
	GrupoModel adicionar(
			@ApiParam(name = "corpo", value = "Representação de um novo Grupo", required = true)
			GrupoInput grupoInput);

	@ApiOperation("Atualiza um Grupo por ID")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "Grupo atualizado"),
		@ApiResponse(code = 404, message = "Geupo não encontrado", response = Problem.class) 
	})
	GrupoModel atualizar(
			@ApiParam(value = "ID de um Grupo", example = "1", required = true)
			Long grupoId, 
			
			@ApiParam(name = "corpo", value = "Representação de um novo Grupo", required = true)
			GrupoInput grupoInput);

	@ApiOperation("Exclui um Grupo por ID")
	@ApiResponses({ 
		@ApiResponse(code = 204, message = "Estado excluído"),			
		@ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class),
		@ApiResponse(code = 400, message = "ID do Estado inválido", response = Problem.class) 
	})
	void remover(
			@ApiParam(value = "ID de um Grupo", example = "1", required = true)
			Long grupoId);
}

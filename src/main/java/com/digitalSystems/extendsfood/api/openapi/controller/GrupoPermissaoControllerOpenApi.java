package com.digitalSystems.extendsfood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.digitalSystems.extendsfood.api.exceptionHandler.Problem;
import com.digitalSystems.extendsfood.api.model.PermissaoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Grupos")
public interface GrupoPermissaoControllerOpenApi {

	@ApiOperation("Lista todas as Permissões de um Grupo")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do Grupo inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
	})
	CollectionModel<PermissaoModel> listar(
			@ApiParam(value = "ID do Grupo", example = "1", required = true)
			Long grupoId);
	
	@ApiOperation("Associada uma Permissões a um Grupo")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Permissão associada ao Grupo com sucesso"),
		@ApiResponse(code = 400, message = "ID do Grupo ou da Permissão inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Grupo ou Permissão não encontrado", response = Problem.class)
	})
	ResponseEntity<Void> associar(
			@ApiParam(value = "ID do Grupo", example = "1", required = true)
			Long grupoId, 
			
			@ApiParam(value = "ID da Permissão", example = "1", required = true)
			Long permissaoId);
	
	@ApiOperation("Desassocia uma Permissão de um Grupo")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Permissão desassociada do Grupo com sucesso"),
		@ApiResponse(code = 400, message = "ID do Grupo ou da Permissão inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Grupo ou Permissão não encontrado", response = Problem.class)
	})
	ResponseEntity<Void> desassociar(
			@ApiParam(value = "ID do Grupo", example = "1", required = true)
			Long grupoId, 
			
			@ApiParam(value = "ID da Permissão", example = "1", required = true)
			Long permissaoId);
}

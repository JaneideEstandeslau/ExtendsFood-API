package com.digitalSystems.extendsfood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.digitalSystems.extendsfood.api.exceptionHandler.Problem;
import com.digitalSystems.extendsfood.api.model.RestauranteBasicoModel;
import com.digitalSystems.extendsfood.api.model.RestauranteModel;
import com.digitalSystems.extendsfood.api.model.inputEntidade.RestauranteInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Restaurantes")
public interface RestauranteControllerOpenApi {

	@ApiOperation(value = "Lista restaurantes")
	@ApiImplicitParams({
	    @ApiImplicitParam(value = "Nome da projeção de pedidos", allowableValues = "apenas-nome, tipo, valor",
	            name = "projecao", paramType = "query", type = "string")
	})
	CollectionModel<RestauranteBasicoModel> listar();

	@ApiOperation("Busca um restaurante por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do restaurante inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class),
		@ApiResponse(code = 400, message = "ID inválido", response = Problem.class)
	})
	RestauranteModel buscar(
			@ApiParam(value = "ID de um restaurante", example = "1", required = true)
			Long restauranteId);

	@ApiOperation("Cadastra um restaurante")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Restaurante cadastrado"),
	})
	RestauranteModel adicionar(
			@ApiParam(name = "corpo", value = "Representação de um novo restaurante", required = true)
			RestauranteInput restauranteInput);
	
	@ApiOperation("Atualiza um restaurante por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Restaurante atualizado"),
		@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class),
		@ApiResponse(code = 400, message = "ID inválido", response = Problem.class)
	})
	RestauranteModel atualizar(
			@ApiParam(value = "ID de um restaurante", example = "1", required = true)
			Long restauranteId, 
			
			@ApiParam(name = "corpo", value = "Representação de um restaurante com os novos dados", required = true)
			RestauranteInput restauranteInput);
	
	@ApiOperation("Ativa um restaurante por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Restaurante ativado com sucesso"),
		@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class),
		@ApiResponse(code = 400, message = "ID inválido", response = Problem.class)
	})
	ResponseEntity<Void> ativar(
			@ApiParam(value = "ID de um restaurante", example = "1", required = true)
			Long restauranteId);
	
	@ApiOperation("Inativa um restaurante por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Restaurante inativado com sucesso"),
		@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class),
		@ApiResponse(code = 400, message = "ID inválido", response = Problem.class)
	})
	ResponseEntity<Void> inativar(
			@ApiParam(value = "ID de um restaurante", example = "1", required = true)
			Long restauranteId);
	
	@ApiOperation("Abre um restaurante por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Restaurante aberto com sucesso"),
		@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class),
		@ApiResponse(code = 400, message = "ID inválido", response = Problem.class)
	})
	ResponseEntity<Void> abrirFuncionamento(
			@ApiParam(value = "ID de um restaurante", example = "1", required = true)
			Long restauranteId);
	
	@ApiOperation("Fecha um restaurante por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Restaurante fechado com sucesso"),
		@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class),
		@ApiResponse(code = 400, message = "ID inválido", response = Problem.class)
	})
	ResponseEntity<Void> fecharFuncionamento(
			@ApiParam(value = "ID de um restaurante", example = "1", required = true)
			Long restauranteId);
}
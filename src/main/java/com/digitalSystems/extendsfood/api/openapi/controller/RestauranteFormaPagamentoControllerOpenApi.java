package com.digitalSystems.extendsfood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.digitalSystems.extendsfood.api.exceptionHandler.Problem;
import com.digitalSystems.extendsfood.api.model.FormaPagamentoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Restaurantes")
public interface RestauranteFormaPagamentoControllerOpenApi {
	
	@ApiOperation("Lista as Formas de Pagamento associadas ao Restaurante")
	@ApiResponses({
		@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class),
		@ApiResponse(code = 400, message = "ID Invalido", response = Problem.class)
	})
	CollectionModel<FormaPagamentoModel> listar(
			@ApiParam(value = "ID do restaurante", example = "1", required = true)
			Long restauranteId);

	@ApiOperation("Desassociação de Restaurante com Forma de Pagamento")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Desassociação realizada com sucesso"),
		@ApiResponse(code = 404, message = "Restaurante ou Forma de Pagamento não encontrado", response = Problem.class),
		@ApiResponse(code = 400, message = "ID do Restaurante ou da Forma de Pagamento Invalido", response = Problem.class)
	})
	ResponseEntity<Void> desassociar(
			@ApiParam(value = "ID do Restaurante", example = "1", required = true)
			Long restauranteId,
			
			@ApiParam(value = "ID da Forma de Pagamento", example = "1", required = true)
			Long formaPagamentoId);

	@ApiOperation("Associação de Restaurante com Forma de Pagamento")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Associação realizada com sucesso"),
		@ApiResponse(code = 404, message = "Restaurante ou forma de pagamento não encontrado", response = Problem.class),
		@ApiResponse(code = 400, message = "ID do Restaurante ou da Forma de Pagamento Invalido", response = Problem.class)
	})
	ResponseEntity<Void> associar(
			@ApiParam(value = "ID do Restaurante", example = "1", required = true)
			Long restauranteId,
			
			@ApiParam(value = "ID da Forma de Pagamento", example = "1", required = true)
			Long formaPagamentoId);

}
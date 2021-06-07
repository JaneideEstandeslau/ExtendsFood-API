package com.digitalSystems.extendsfood.api.openapi.controller;

import com.digitalSystems.extendsfood.api.exceptionHandler.Problem;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Pedidos")
public interface FluxoPedidoControllerOpenApi {

	@ApiOperation("Confirma o Pedido")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do Pedido inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Pedido não encontrado", response = Problem.class),
		@ApiResponse(code = 204, message = "Pedido condirmado com sucesso", response = Problem.class)
	})
	void confirmarPedido(
			@ApiParam(value = "ID de um Pedido", example = "1", required = true)
			Long pedidoId);
	
	@ApiOperation("Registrar saída para entrega")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do Pedido inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Pedido não encontrado", response = Problem.class),
		@ApiResponse(code = 204, message = "Pedido saiu para entrega", response = Problem.class)
	})
	void pedidoSaiuParaEntrega(
			@ApiParam(value = "ID de um Pedido", example = "1", required = true)
			Long pedidoId);
	
	@ApiOperation("Registrar entrega do Pedido")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do Pedido inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Pedido não encontrado", response = Problem.class),
		@ApiResponse(code = 204, message = "Pedido entregue com sucesso", response = Problem.class)
	})
	void entregarPedido(
			@ApiParam(value = "ID de um Pedido", example = "1", required = true)
			Long pedidoId);
	
	@ApiOperation("Registrar entrega do Pedido")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do Pedido inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Pedido não encontrado", response = Problem.class),
		@ApiResponse(code = 204, message = "Pedido entregue com sucesso", response = Problem.class)
	})
	void cancelarPedido(
			@ApiParam(value = "ID de um Pedido", example = "1", required = true)
			Long pedidoId);
}

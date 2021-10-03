package com.digitalSystems.extendsfood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.digitalSystems.extendsfood.api.openapi.controller.FluxoPedidoControllerOpenApi;
import com.digitalSystems.extendsfood.domain.service.FluxoPedidoService;

@RestController
@RequestMapping(path = "/pedidos/{pedidoId}", produces = MediaType.APPLICATION_JSON_VALUE)
public class FluxoPedidoController implements FluxoPedidoControllerOpenApi{

	@Autowired
	private FluxoPedidoService fluxoPedidoService;
	
	@PutMapping("/confirmar")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> confirmarPedido(@PathVariable Long pedidoId) {
		fluxoPedidoService.confirmarPedido(pedidoId);
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/saiu-para-entrega")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> pedidoSaiuParaEntrega(@PathVariable Long pedidoId) {
		fluxoPedidoService.pedidoSaiuParaEntrega(pedidoId);
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/entregar")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> entregarPedido(@PathVariable Long pedidoId) {
		fluxoPedidoService.entregarPedido(pedidoId);
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/cancelar")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> cancelarPedido(@PathVariable Long pedidoId) {
		fluxoPedidoService.cancelarPedido(pedidoId);
		
		return ResponseEntity.noContent().build();
	}
}

package com.digitalSystems.extendsfood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.digitalSystems.extendsfood.domain.service.FluxoPedidoService;

@RestController
@RequestMapping("/pedidos/{pedidoId}")
public class FluxoPedidoController {

	@Autowired
	private FluxoPedidoService fluxoPedidoService;
	
	@PutMapping("/confirmar")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void confirmarPedido(@PathVariable Long pedidoId) {
		fluxoPedidoService.confirmarPedido(pedidoId);
	}
	
	@PutMapping("/saiu-para-entrega")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void pedidoSaiuParaEntrega(@PathVariable Long pedidoId) {
		fluxoPedidoService.pedidoSaiuParaEntrega(pedidoId);
	}
	
	@PutMapping("/entregar")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void entregarPedido(@PathVariable Long pedidoId) {
		fluxoPedidoService.entregarPedido(pedidoId);
	}
	
	@PutMapping("/cancelar")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void cancelarPedido(@PathVariable Long pedidoId) {
		fluxoPedidoService.cancelarPedido(pedidoId);
	}
}

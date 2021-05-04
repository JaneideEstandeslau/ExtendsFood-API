package com.digitalSystems.extendsfood.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalSystems.extendsfood.domain.model.Pedido;

@Service
public class FluxoPedidoService {
	
	@Autowired
	private EmissaoPedidoService pedidoService;
	
	@Transactional
	public void confirmarPedido(Long pedidoId) {
		
		Pedido pedido = pedidoService.buscarOuFalhar(pedidoId);
		pedido.confirmar();
	}
	
	@Transactional
	public void pedidoSaiuParaEntrega(Long pedidoId) {
		
		Pedido pedido = pedidoService.buscarOuFalhar(pedidoId);
		pedido.SaiuParaEntrega();
	}
	
	@Transactional
	public void entregarPedido(Long pedidoId) {
		
		Pedido pedido = pedidoService.buscarOuFalhar(pedidoId);
		pedido.entregar();
	}
	
	@Transactional
	public void cancelarPedido(Long pedidoId) {
		
		Pedido pedido = pedidoService.buscarOuFalhar(pedidoId);
		pedido.cancelar();
	}

}

package com.digitalSystems.extendsfood.domain.model.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.digitalSystems.extendsfood.domain.model.Pedido;
import com.digitalSystems.extendsfood.domain.model.enums.StatusPedido;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PedidoUsuario {
	
	
	private LocalDate data;
	
	private List<PedidoResumo> pedidos;
	
	public void adicionarPedidos(List<Pedido> pedidos) {
		
		List<PedidoResumo> pedidosResumo = new ArrayList<>();
		
		pedidos.forEach(pedido -> {
			
			Long id = pedido.getId();
			String restauranteNome = pedido.getRestaurante().getNome();
			StatusPedido status = pedido.getStatus();
			
			PedidoResumo pedidoResumo = PedidoResumo.builder()
				.id(id)
				.status(status)
				.restauranteNome(restauranteNome)
				.build();
			
			pedidoResumo.adicionarItens(pedido.getItensPedido());
			
			pedidosResumo.add(pedidoResumo);
			
		});
		
		this.pedidos = pedidosResumo;
	}
	

}

package com.digitalSystems.extendsfood.domain.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.digitalSystems.extendsfood.domain.model.ItemPedido;
import com.digitalSystems.extendsfood.domain.model.enums.StatusPedido;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PedidoResumo {

	private Long id;
	private String restauranteNome;
	private StatusPedido status;
	private List<ItemPedidoResumo> itensPedido;
	
	public void adicionarItens(List<ItemPedido> itensPedido) {
		
		List<ItemPedidoResumo> itensPedidoResumo = new ArrayList<>();
		
		itensPedido.forEach(itemPedido -> {
			
			Integer quantidade = itemPedido.getQuantidade();
			String produtoNome = itemPedido.getProduto().getNome();
			
			ItemPedidoResumo itemPedidoResumo = ItemPedidoResumo.builder()
				.quantidade(quantidade)
				.produtoNome(produtoNome)
				.build();
			
			itensPedidoResumo.add(itemPedidoResumo);
			
		});
		
		this.itensPedido = itensPedidoResumo;
	}


}
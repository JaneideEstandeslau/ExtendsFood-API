package com.digitalSystems.extendsfood.domain.event;

import com.digitalSystems.extendsfood.domain.model.Pedido;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PedidoCanceladoEvent {

	private Pedido pedido;
	
}
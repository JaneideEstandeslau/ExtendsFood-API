
package com.digitalSystems.extendsfood.domain.model.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ItemPedidoResumo {

	private Integer quantidade;
	private String produtoNome;
}
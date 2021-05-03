package com.digitalSystems.extendsfood.api.model;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoModel {

	private Long id;
	
	private Long produtoId;
	
	private String produtoNome;
	
	private BigDecimal precoUnitario;

	private BigDecimal precoTotal;

	private Integer quantidade;
	
	private List<ItemComplementoResumoModel> itensComplemento;
}

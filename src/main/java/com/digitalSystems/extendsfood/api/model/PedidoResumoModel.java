package com.digitalSystems.extendsfood.api.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoResumoModel {

	
	private Long id;
	
	private BigDecimal subTotal;
	
	private BigDecimal taxaFrete;
	
	private BigDecimal valorTotal;
	
	private LocalDateTime dataCriacao;
	
	private String status;
	
	private RestauranteResumoModel restaurante;
	
	private UsuarioModel cliente;
	
	@JsonProperty("produtos")
	private List<ItemPedidoResumoModel> itensPedido;
}

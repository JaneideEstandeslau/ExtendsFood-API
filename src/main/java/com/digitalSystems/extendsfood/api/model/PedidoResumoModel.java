package com.digitalSystems.extendsfood.api.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoResumoModel {

	
	@ApiModelProperty(example = "1", position = 5)
	private Long id;
	
	@ApiModelProperty(example = "10.00", position = 10)
	private BigDecimal taxaFrete;
	
	@ApiModelProperty(example = "10.00", position = 15)
	private BigDecimal subTotal;	
	
	@ApiModelProperty(example = "20.00", position = 20)
	private BigDecimal valorTotal;
	
	@ApiModelProperty(example = "04/06/2021 14:20:56", position = 25)
	private LocalDateTime dataCriacao;
	
	@ApiModelProperty(example = "ENTREGUE", position = 25)
	private String status;
	
	@ApiModelProperty(position = 30)
	private RestauranteResumoModel restaurante;
	
	@ApiModelProperty( position = 35)
	private UsuarioModel cliente;
	
	@ApiModelProperty(position = 40)
	@JsonProperty("produtos")
	private List<ItemPedidoResumoModel> itensPedido;
}

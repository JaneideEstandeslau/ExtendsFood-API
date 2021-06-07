package com.digitalSystems.extendsfood.api.model;

import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoModel {

	@ApiModelProperty(example = "1", position = 5)
	private Long id;
	
	@ApiModelProperty(example = "1", position = 10)
	private Long produtoId;
	
	@ApiModelProperty(example = "Pizza de Calabresa", position = 15)
	private String produtoNome;
	
	@ApiModelProperty(example = "78.90", position = 20)
	private BigDecimal precoUnitario;

	@ApiModelProperty(example = "394.50", position = 25)
	private BigDecimal precoTotal;

	@ApiModelProperty(example = "5", position = 30)
	private Integer quantidade;
	
	@ApiModelProperty(position = 35)
	private List<ItemComplementoResumoModel> itensComplemento;
}

package com.digitalSystems.extendsfood.api.model;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoResumoModel extends RepresentationModel<ItemPedidoResumoModel>{
	
	@ApiModelProperty(example = "1", position = 5)
	private Long produtoId;
	
	@ApiModelProperty(example = "Pizza de Calabresa", position = 5)
	private String produtoNome;
}

package com.digitalSystems.extendsfood.api.model;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemComplementoModel {

	@ApiModelProperty(example = "1", position = 5)
	private Long id;
	
	@ApiModelProperty(example = "Calabre de Portuguesa", position = 10)
	private String nome;
	
	@ApiModelProperty(example = "5.00", position = 15)
	private BigDecimal preco;
	
	@ApiModelProperty(example = "true", position = 20)
	private Boolean disponivel;

}

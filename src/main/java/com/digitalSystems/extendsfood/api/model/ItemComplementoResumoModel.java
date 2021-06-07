package com.digitalSystems.extendsfood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemComplementoResumoModel {

	@ApiModelProperty(example = "Calabresa portuguesa", position = 5)
	private String nome;
	
}

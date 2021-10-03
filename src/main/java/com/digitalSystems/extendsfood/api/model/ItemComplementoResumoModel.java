package com.digitalSystems.extendsfood.api.model;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemComplementoResumoModel extends RepresentationModel<ItemComplementoResumoModel>{

	@ApiModelProperty(example = "Calabresa portuguesa", position = 5)
	private String nome;
	
}

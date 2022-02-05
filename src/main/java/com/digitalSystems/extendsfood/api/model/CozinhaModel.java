package com.digitalSystems.extendsfood.api.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "cozinhas")
@Getter
@Setter
public class CozinhaModel extends RepresentationModel<CozinhaModel>{

	@ApiModelProperty(example = "1", position = 5)
	private Long id;
	
	@ApiModelProperty(example = "Pizzaria", position = 10)
	private String nome;
}

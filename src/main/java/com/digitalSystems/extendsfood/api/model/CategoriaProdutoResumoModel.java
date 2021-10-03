package com.digitalSystems.extendsfood.api.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "categorias")
@Getter
@Setter
public class CategoriaProdutoResumoModel extends RepresentationModel<CategoriaProdutoResumoModel>{

	@ApiModelProperty(example = "1", position = 5)
	private Long id;

	@ApiModelProperty(example = "Pizza tradicional", position = 10)
	private String descricao;
	
	@ApiModelProperty(position = 15)
	private RestauranteResumoModel restaurante;
}

package com.digitalSystems.extendsfood.api.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "permissoes")
@Getter
@Setter
public class PermissaoModel extends RepresentationModel<PermissaoModel>{

	@ApiModelProperty(example = "1", position = 5)
	private Long id;

	@ApiModelProperty(example = "CRUD_COZINHA", position = 10)
	private String nome;

	@ApiModelProperty(example = "Permite realizar CRUD em cozinhas", position = 15)
	private String descricao;
}

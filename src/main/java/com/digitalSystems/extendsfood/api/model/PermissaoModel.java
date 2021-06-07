package com.digitalSystems.extendsfood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissaoModel {

	@ApiModelProperty(example = "1", position = 5)
	private Long id;

	@ApiModelProperty(example = "CRUD_COZINHA", position = 10)
	private String nome;

	@ApiModelProperty(example = "Permite realizar CRUD em cozinhas", position = 15)
	private String descricao;
}

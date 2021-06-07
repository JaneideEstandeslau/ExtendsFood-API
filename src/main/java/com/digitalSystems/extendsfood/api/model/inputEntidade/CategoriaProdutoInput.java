package com.digitalSystems.extendsfood.api.model.inputEntidade;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaProdutoInput {

	@ApiModelProperty(example = "Pizza tradicional", position = 10, required = true)
	@NotBlank
	private String descricao;
}

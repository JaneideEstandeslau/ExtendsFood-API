package com.digitalSystems.extendsfood.api.model.inputRelacionamento;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemComplementoIdInput {

	@ApiModelProperty(example = "1", position = 5)
	@NotNull
	private Long id;
}

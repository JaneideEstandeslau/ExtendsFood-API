package com.digitalSystems.extendsfood.api.model.inputEntidade;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoInput {

	@ApiModelProperty(example = "Cartão de Credito", required = true, position = 5)
	@NotBlank
	private String descricao;
}

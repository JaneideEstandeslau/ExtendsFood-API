package com.digitalSystems.extendsfood.api.model.inputEntidade;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoInput {

	@NotBlank
	private String descricao;
}

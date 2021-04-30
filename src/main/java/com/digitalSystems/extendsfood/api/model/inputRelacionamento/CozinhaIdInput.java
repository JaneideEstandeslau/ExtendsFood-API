package com.digitalSystems.extendsfood.api.model.inputRelacionamento;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaIdInput {

	@NotNull
	private Long id;
}

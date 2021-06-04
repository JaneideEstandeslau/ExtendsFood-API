package com.digitalSystems.extendsfood.api.model.inputEntidade;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.digitalSystems.extendsfood.api.model.inputRelacionamento.EstadoIdInput;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeInput {

	@ApiModelProperty(example = "Campina Grande", required = true)
	@NotBlank
	private String nome;
	
	@Valid
	@NotNull
	private EstadoIdInput estado;
}

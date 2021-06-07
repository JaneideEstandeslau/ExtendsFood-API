package com.digitalSystems.extendsfood.api.model.inputEntidade;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.digitalSystems.extendsfood.api.model.inputRelacionamento.CidadeIdInput;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoInput {

	@ApiModelProperty(example = "58.410-393", required = true, position = 5)
	@NotBlank
	private String cep;

	@ApiModelProperty(example = "Barão da Passagem", required = true, position = 10)
	@NotBlank
	private String rua;

	@ApiModelProperty(example = "19", position = 15)
	@NotNull
	private String numero;

	@ApiModelProperty(example = "APTO 100", position = 20)
	private String complemento;

	@ApiModelProperty(example = "true", required = true, position = 25)
	@NotBlank
	private String bairro;
	
	@ApiModelProperty(position = 30)
	@Valid
	@NotNull
	private CidadeIdInput cidade;
	
}

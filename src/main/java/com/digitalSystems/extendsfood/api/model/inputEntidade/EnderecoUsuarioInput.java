package com.digitalSystems.extendsfood.api.model.inputEntidade;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.digitalSystems.extendsfood.api.model.inputRelacionamento.CidadeIdInput;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoUsuarioInput {

	@NotBlank
	private String cep;

	@NotBlank
	private String rua;

	@NotNull
	private String numero;

	private String complemento;

	@NotBlank
	private String bairro;
	
	@Valid
	@NotNull
	private CidadeIdInput cidade;
	
}

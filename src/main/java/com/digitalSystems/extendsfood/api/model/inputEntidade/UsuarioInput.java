package com.digitalSystems.extendsfood.api.model.inputEntidade;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInput {

	@CPF
	@NotBlank
	private String cpf;
	
	@NotBlank
	private String nome;
	
	@Email
	@NotBlank
	private String email;
}
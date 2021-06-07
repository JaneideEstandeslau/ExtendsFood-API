package com.digitalSystems.extendsfood.api.model.inputEntidade;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CPF;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInput {

	@ApiModelProperty(example = "892.798.690-35", required = true, position = 5)
	@CPF
	@NotBlank
	private String cpf;
	
	@ApiModelProperty(example = "Janeide Estandeslau", required = true, position = 10)
	@NotBlank
	private String nome;
	
	@ApiModelProperty(example = "janeide@gmail.com", required = true, position = 15)
	@Email
	@NotBlank
	private String email;
	
}

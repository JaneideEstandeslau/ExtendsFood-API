package com.digitalSystems.extendsfood.api.model.inputEntidade;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioComSenhaInput extends UsuarioInput{

	@ApiModelProperty(example = "123456", position = 5)
	@NotBlank
	private String senha;
}

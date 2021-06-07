package com.digitalSystems.extendsfood.api.model;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioEnderecoModel {

	@ApiModelProperty(example = "1", position = 5)
	private Long id;

	@ApiModelProperty(example = "892.798.690-35", position = 10)
	private String cpf;
	
	@ApiModelProperty(example = "Janeide Estandeslau", position = 15)
	private String nome;
	
	@ApiModelProperty(example = "janeide@gmail.com", position = 20)
	private String email;
	
	@ApiModelProperty(position = 25)
	private List<EnderecoUsuarioModel> enderecos;
}

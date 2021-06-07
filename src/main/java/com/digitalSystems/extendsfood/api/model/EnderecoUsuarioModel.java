package com.digitalSystems.extendsfood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoUsuarioModel {

	@ApiModelProperty(example = "1", position = 5)
	private Long id;
	
	@ApiModelProperty(example = "true", position = 10)
	private Boolean enderecoAtivoUsuario;
	
	@ApiModelProperty(example = "58.410-393", position = 15)
	private String cep;
	
	@ApiModelProperty(example = "Barão da Passagem", position = 20)
	private String rua;
	
	@ApiModelProperty(example = "19", position = 25)
	private String numero;
	
	@ApiModelProperty(example = "APTO 100", position = 30)
	private String complemento;
	
	@ApiModelProperty(example = "Centro", position = 35)
	private String bairro;	
	
	@ApiModelProperty(position = 40)
	private CidadeResumoModel cidade;
}

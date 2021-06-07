package com.digitalSystems.extendsfood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoModel {

	@ApiModelProperty(example = "1", position = 5)
	private Long id;
	
	@ApiModelProperty(example = "58.140-393", position = 10)
	private String cep;
	
	@ApiModelProperty(example = "Barão da Passage,", position = 15)
	private String rua;
	
	@ApiModelProperty(example = "19", position = 20)
	private String numero;
	
	@ApiModelProperty(example = "APTO", position = 25)
	private String complemento;
	
	@ApiModelProperty(example = "Centro", position = 30)
	private String bairro;
	
	@ApiModelProperty(position = 35)
	private CidadeResumoModel cidade;
}

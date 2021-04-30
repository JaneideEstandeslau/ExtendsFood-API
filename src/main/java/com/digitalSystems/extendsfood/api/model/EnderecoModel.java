package com.digitalSystems.extendsfood.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoModel {

	private Long id;
	
	private String cep;
	
	private String rua;
	
	private String numero;
	
	private String complemento;
	
	private String bairro;
	
	private CidadeResumoModel cidade;
}

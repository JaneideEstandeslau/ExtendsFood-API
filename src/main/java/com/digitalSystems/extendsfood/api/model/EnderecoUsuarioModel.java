package com.digitalSystems.extendsfood.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoUsuarioModel {

	private Long id;
	
	private Boolean enderecoAtivoUsuario;
	
	private String cep;
	
	private String rua;
	
	private String numero;
	
	private String complemento;
	
	private String bairro;	
	
	private CidadeResumoModel cidade;
}

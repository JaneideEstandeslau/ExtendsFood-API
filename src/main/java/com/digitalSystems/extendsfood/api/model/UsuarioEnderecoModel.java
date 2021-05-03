package com.digitalSystems.extendsfood.api.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioEnderecoModel {

	private Long id;

	private String cpf;
	
	private String nome;
	
	private String email;
	
	private List<EnderecoUsuarioModel> enderecos;
}

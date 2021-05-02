package com.digitalSystems.extendsfood.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioModel {

	private Long id;

	private String cpf;
	
	private String nome;
	
	private String email;
}

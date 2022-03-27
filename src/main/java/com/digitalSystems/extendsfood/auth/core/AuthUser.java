package com.digitalSystems.extendsfood.auth.core;

import java.util.Collections;

import org.springframework.security.core.userdetails.User;

import com.digitalSystems.extendsfood.auth.model.Usuario;

import lombok.Getter;

//Contem as informações do usuário logado
//Classe criada porque nem toda as informações que precisamos existem no User
//então criamos a classe para conter essas informações
@Getter
public class AuthUser extends User {

	private static final long serialVersionUID = 1L;
	
	private Long userId;
	private String fullName;

	public AuthUser(Usuario usuario) {
		super(usuario.getEmail(), usuario.getSenha(), Collections.emptyList());

		this.userId = usuario.getId();
		this.fullName = usuario.getNome();
	}

}

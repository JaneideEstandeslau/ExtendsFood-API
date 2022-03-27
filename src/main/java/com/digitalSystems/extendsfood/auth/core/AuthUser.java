package com.digitalSystems.extendsfood.auth.core;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
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

	//authorities são as permissoes dos usuários
	public AuthUser(Usuario usuario, Collection<? extends GrantedAuthority> authorities) {
		super(usuario.getEmail(), usuario.getSenha(), authorities);

		this.userId = usuario.getId();
		this.fullName = usuario.getNome();
	}

}

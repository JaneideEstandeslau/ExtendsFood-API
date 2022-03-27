package com.digitalSystems.extendsfood.core.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class ExtendsSecurity {
	
	private Authentication getAuthentication() {
		//Pega o contexto atual de segurança
		//Retorna o Token
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	public Long getUsuarioId() {		
		Jwt jwt = (Jwt) getAuthentication().getPrincipal();
		return jwt.getClaim("usuario_id");
	}

}

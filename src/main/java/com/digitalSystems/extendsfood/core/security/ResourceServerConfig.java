package com.digitalSystems.extendsfood.core.security;

import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

@Configuration
@EnableWebSecurity //Adiciona as configurações do Spring Security
@EnableGlobalMethodSecurity(//Habilita a funcionalidade de restrição de acesso nos metodos
	prePostEnabled = true //habilita as anotações pré/pós do Spring Security
)
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {//Classe de configuração base do Spring Security

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.cors().and()
			.oauth2ResourceServer() //Configura o suporte ao resource server no projeto (projeto é um resource server)
				.jwt() //Utiliza os token JWT para dar acesso ao endpoints
				.jwtAuthenticationConverter(jwtAuthenticationConverter());
	}	
	
	//extrai as permissoes do usuário do token e converter em GrantedAuthority
	private JwtAuthenticationConverter jwtAuthenticationConverter() {
		
		var jwtAuthenticationConverter = new JwtAuthenticationConverter();
		
		//define as autorizações concedidas a esse usuário
		jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwt -> {
			
			var authorities = jwt.getClaimAsStringList("authorities");
			
			if (authorities == null) {
				authorities = Collections.emptyList();
			}
			
			return authorities.stream()
					.map(SimpleGrantedAuthority::new)
					.collect(Collectors.toList());
		});
		
		return jwtAuthenticationConverter;
	}
	
}

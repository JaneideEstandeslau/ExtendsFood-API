package com.digitalSystems.extendsfood.core.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class ResourceServerConfig extends WebSecurityConfigurerAdapter{

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.anyRequest().authenticated()//Autriza qualquer requisição autorizada
		.and()
		.cors().and()
		.oauth2ResourceServer() //Configura o suporte ao resource server no projeto (projeto é um resource server)
			.opaqueToken(); //Utiliza os token para dar acesso ao endpoints
	}
	
	
	
}

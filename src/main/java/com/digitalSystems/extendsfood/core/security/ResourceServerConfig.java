package com.digitalSystems.extendsfood.core.security;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

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
			.jwt(); //Utiliza os token JWT para dar acesso ao endpoints
	}
	
	//Responsavel pela decodificação do JWT
	@Bean
	public JwtDecoder jetDecoder() {
		
		String chave = "jhgfh35gj8g3h1jkgiughfdbjhhfdgdh23189156789dfbshfghjkbsgh";
		String metodoCriptografico = "HmacSHA256";
		
		var secretKey = new SecretKeySpec(chave.getBytes(), metodoCriptografico);
		
		return NimbusJwtDecoder.withSecretKey(secretKey).build();
	}
	
	
	
}

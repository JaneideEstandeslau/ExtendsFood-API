package com.digitalSystems.extendsfood.core.security;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

@Configuration
@EnableWebSecurity //Adiciona as configurações do Spring Security
@EnableGlobalMethodSecurity(//Habilita a funcionalidade de restrição de acesso nos metodos
	prePostEnabled = true //habilita as anotações pré/pós do Spring Security
)
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {//Classe de configuração base do Spring Security

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.formLogin().loginPage("/login") //existe um formulário de login que deve ser usado na request /oauth
			.and()
			.authorizeRequests()
				.antMatchers("/oauth/**").authenticated()//Precisa estar autemtica para acesar o /oauth
			.and()
			.csrf().disable()
			.cors().and()
			.oauth2ResourceServer() //Configura o suporte ao resource server no projeto (projeto é um resource server)
				.jwt() //Utiliza os token JWT para dar acesso ao endpoints
				.jwtAuthenticationConverter(jwtAuthenticationConverter());
	}	
	
	//Carrega as permissoes do usuário do token e converter em GrantedAuthority
	private JwtAuthenticationConverter jwtAuthenticationConverter() {
		
		var jwtAuthenticationConverter = new JwtAuthenticationConverter();
		
		//define as autorizações concedidas ao usuário e os escopos concedidos ao cliente
		jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwt -> {
			
			var authorities = jwt.getClaimAsStringList("authorities");
			
			if (authorities == null) {
				authorities = Collections.emptyList();
			}
			
			//Carrega as Perssions
			Collection<GrantedAuthority> grantedAuthoritiesPermission = 
					authorities.stream()
					.map(SimpleGrantedAuthority::new)
					.collect(Collectors.toList());
			
			//Carrega os SCOPES
			var scopesAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
			Collection<GrantedAuthority> grantedAuthoritiesScope = scopesAuthoritiesConverter.convert(jwt);
			
			grantedAuthoritiesScope.addAll(grantedAuthoritiesPermission);
			
			return grantedAuthoritiesScope;
		});
		
		return jwtAuthenticationConverter;
	}
	
	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	
}

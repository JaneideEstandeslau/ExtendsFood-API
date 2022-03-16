package com.digitalSystems.extendsfood.auth;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;

@Configuration
@EnableAuthorizationServer // Habilita o authorizarion server
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {// classe herdade para fazer
																						// algumas configuraçõe

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;

	// configura os clients que podem acessar esse authorization server
	// configura os clientes que são permitidos acessar do authorization server e
	// receber o access token
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients
			.inMemory()
				.withClient("extendsfood-web")// identificação do cliente
				.secret(passwordEncoder.encode("web123"))
				.authorizedGrantTypes("password", "refresh_token")//Fluxos
				.scopes("write", "read")
				.accessTokenValiditySeconds(60 * 60 * 6) // validade de 6 horas
				.refreshTokenValiditySeconds(24 * 60 * 60) // validade de 1 dia
				
			.and()
				.withClient("foodanalytics")
				.secret(passwordEncoder.encode("analitics23"))
				.authorizedGrantTypes("authorization_code")
				.scopes("write", "read")
				.redirectUris("http://aplicacao-cliente")
				
				//URL para slicitar code sem PKCE
				//http://localhost:8081/oauth/authorize?response_type=code&client_id=foodanalytics&state=abc&redirect_uri=http://aplicacao-cliente
				
				//URL para solicitar code com PKCE e com method plain
				//http://localhost:8081/oauth/authorize?response_type=code&client_id=foodanalytics&redirect_uri=http://aplicacao-cliente&code_challenge=aasbfdifbdkld652165dgdfgjdfkhghdjfhg&code_challenge_method=plain
				
				//URL para solicitar code com PKCE e com method s256
				//http://localhost:8081/oauth/authorize?response_type=code&client_id=foodanalytics&redirect_uri=http://aplicacao-cliente&code_challenge=eKNGQmU3qETjyVHlLNNlaCKOEIZPXfow-lN7RVUv6Js&code_challenge_method=s256
			.and()
				.withClient("webadmin")
				.authorizedGrantTypes("implicit")
				.scopes("write", "read")
				.redirectUris("http://aplicacao-cliente")
				//http://localhost:8081/oauth/authorize?response_type=token&client_id=webadmin&state=abc&redirect_uri=http://aplicacao-cliente
				
			.and()
				.withClient("aplicacao-back-externa")
				.secret(passwordEncoder.encode("externa123"))
				.authorizedGrantTypes("client_credentials")
				.scopes("write", "read")
			.and()
				.withClient("checktoken")
				.secret(passwordEncoder.encode("check123"));// Para evitar que o rsource server se autentique como um
															// client foi diado um client só para o resource server
	}
	
	//Configura o acesso ao endpoints de autorização
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//		security.checkTokenAccess("isAuthenticated()"); //expressão de segurança
		security.checkTokenAccess("permitAll()");//Quem tem acesso ao check token? Quem estiver autenticado
//		.allowFormAuthenticationForClients();//Habilita para o client não precisar informar a secret
		
	}

	//AuthorizationServerEndpointsConfigurer precisa de um authenticationManager
	//É através do authenticationManager que o autorization server
	//validade o usuário e senha do usuário final que é passado na autenticação
	//UserDetailsService Interface principal que carrega dados específicos do usuário. Necessario para o refresh_token.
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
			.authenticationManager(authenticationManager)//Usado no password
			.userDetailsService(userDetailsService)//Usado no refresh token
			.reuseRefreshTokens(false) //Configura a não reutilização de refresh token
			.tokenGranter(tokenGranter(endpoints));
	}
	
	//Configura todos os TokenGranter mais o PKCE
	// Instancia o Authorization Code com PKCE e retorna a instancia do TokenGranter
	// com o PKCE e todos os outros TokenGranter client_credentials, implicit
	private TokenGranter tokenGranter(AuthorizationServerEndpointsConfigurer endpoints) {
		var pkceAuthorizationCodeTokenGranter = new PkceAuthorizationCodeTokenGranter(endpoints.getTokenServices(),
				endpoints.getAuthorizationCodeServices(), endpoints.getClientDetailsService(),
				endpoints.getOAuth2RequestFactory());
		
		var granters = Arrays.asList(
				pkceAuthorizationCodeTokenGranter, endpoints.getTokenGranter());
		
		return new CompositeTokenGranter(granters);
	}
}

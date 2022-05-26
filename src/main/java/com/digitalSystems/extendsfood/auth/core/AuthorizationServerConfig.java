package com.digitalSystems.extendsfood.auth.core;

import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
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
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@Configuration
@EnableAuthorizationServer // Habilita o authorizarion server
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {// classe herdade para fazer
																						// algumas configuraçõe

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtKeyStoreProperties jwtKeyStoreProperties; 
	
	@Autowired
	private DataSource dataSource;
	

	// configura os clients que podem acessar esse authorization server
	// configura os clientes que são permitidos acessar do authorization server e
	// receber o access token
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.jdbc(dataSource);
	}
	
	//Configura o acesso ao endpoints de autorização
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//		security.checkTokenAccess("isAuthenticated()"); //expressão de segurança
		security.checkTokenAccess("permitAll()")//Quem tem acesso ao check token? Quem estiver autenticado
		.tokenKeyAccess("permitAll()");//Libera acesso a chave publica
//		.allowFormAuthenticationForClients();//Habilita para o client não precisar informar a secret
		
	}

	//AuthorizationServerEndpointsConfigurer precisa de um authenticationManager
	//É através do authenticationManager que o autorization server
	//validade o usuário e senha do usuário final que é passado na autenticação
	//UserDetailsService Interface principal que carrega dados específicos do usuário. Necessario para o refresh_token.
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		
		var enhancerChain = new TokenEnhancerChain();
		enhancerChain.setTokenEnhancers(Arrays.asList(new JwtCustomClaimsTokenEnhancer(), jwtAccessTokenConverter()));
		
		endpoints
			.authenticationManager(authenticationManager)//Usado no password
			.userDetailsService(userDetailsService)//Carrega informações do usuáio
			.reuseRefreshTokens(false) //Configura a não reutilização de refresh token
			.accessTokenConverter(jwtAccessTokenConverter())//Converte o token para JWT
			.tokenEnhancer(enhancerChain)//Adiciona novas informações ao payload do JWT
			.approvalStore(approvalStore(endpoints.getTokenStore()))
			.tokenGranter(tokenGranter(endpoints));//Adiciona o desafio PKCE
	}
	
	//Vai converte as informações de um usuário logado para JWT e vice-versa
	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		
		var jwtAccessTokenConverter = new JwtAccessTokenConverter();
		
		var jksResource = new ClassPathResource(jwtKeyStoreProperties.getPath());//Referencia o arquivo JKS
		var keyStorePass = jwtKeyStoreProperties.getPassword();//Senha para abrir o arquivo JKS
		var keyPairAlias = jwtKeyStoreProperties.getKaypairAlias();//Identificação para o par de chaves
		
		//Abre o arquivo JKS
		var keyStoreKeyFactory = new KeyStoreKeyFactory(jksResource, keyStorePass.toCharArray());
		
		var keyPair = keyStoreKeyFactory.getKeyPair(keyPairAlias); //pega o par de chave
		
		jwtAccessTokenConverter.setKeyPair(keyPair);
		
		return jwtAccessTokenConverter;
	}
	
	private ApprovalStore approvalStore(TokenStore tokenStore) {
		var approvalStore = new TokenApprovalStore();
		approvalStore.setTokenStore(tokenStore);
		
		return approvalStore;
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

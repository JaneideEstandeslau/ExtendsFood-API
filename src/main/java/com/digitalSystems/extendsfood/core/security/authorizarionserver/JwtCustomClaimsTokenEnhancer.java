package com.digitalSystems.extendsfood.core.security.authorizarionserver;

import java.util.HashMap;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

//Adiciona novas informações no payload do token JWT amtes dele ser assinado
public class JwtCustomClaimsTokenEnhancer implements TokenEnhancer{

	//incrementa o access token
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		//Nem todos os sempre temos um usuário logado, como uma API externa que acessa a API		
		if(authentication.getPrincipal() instanceof AuthUser) {
			
			//Usuário logado
			var authUser = (AuthUser) authentication.getPrincipal();
			
			//MAP com as melhorias
			var info = new HashMap<String, Object>();
			info.put("nome_completo", authUser.getFullName());
			info.put("usuario_id", authUser.getUserId());
			
			//adiciona as melhorias no token
			var oAuth2AccessToken = (DefaultOAuth2AccessToken) accessToken;
			oAuth2AccessToken.setAdditionalInformation(info);
		}
		
		return accessToken;
	}

}

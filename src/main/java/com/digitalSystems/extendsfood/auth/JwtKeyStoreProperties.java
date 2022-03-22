package com.digitalSystems.extendsfood.auth;


import javax.validation.constraints.NotBlank;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Validated
@Component
@ConfigurationProperties("extends.jwt.keystore")
public class JwtKeyStoreProperties {
	
	@NotBlank
	private String path;
	
	@NotBlank
	private String password;
	
	@NotBlank
	private String kaypairAlias;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getKaypairAlias() {
		return kaypairAlias;
	}

	public void setKaypairAlias(String kaypairAlias) {
		this.kaypairAlias = kaypairAlias;
	}

}

package com.digitalSystems.extendsfood.core.email;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

@Validated
@Getter
@Setter
@Component
@ConfigurationProperties("extendsfood.email")
public class EmailProperties {

	@NotNull
	private String remetente;
	
	private String destinatario;
	
	private Implementacao impl = Implementacao.FAKE;
	
	
	public enum Implementacao {
	    SMTP, FAKE
	}
	
}
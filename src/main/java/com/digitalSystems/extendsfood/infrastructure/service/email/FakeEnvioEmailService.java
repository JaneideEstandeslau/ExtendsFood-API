package com.digitalSystems.extendsfood.infrastructure.service.email;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FakeEnvioEmailService extends SmtpEnvioEmailService{

	@Override
	public void enviar(Mensagem mensagem) {
		
		String corpo = processarTemplate(mensagem);

        log.info("[FAKE E-MAIL] Para: {}\n{}", mensagem.getDestinatarios(), corpo);	
	}

}

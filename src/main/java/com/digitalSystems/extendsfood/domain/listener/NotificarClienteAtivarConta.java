package com.digitalSystems.extendsfood.domain.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.digitalSystems.extendsfood.domain.event.ConfirmarCadastradoEvent;
import com.digitalSystems.extendsfood.domain.service.EnvioEmailService;
import com.digitalSystems.extendsfood.domain.service.EnvioEmailService.Mensagem;

@Component
public class NotificarClienteAtivarConta implements ApplicationListener<ConfirmarCadastradoEvent> {

	@Autowired
	private EnvioEmailService envioEmail;
	
	@Override
	public void onApplicationEvent(ConfirmarCadastradoEvent event) {
		
		var mensagem = Mensagem.builder()
				.assunto("Confirmação de Cadastro")
				.templatePath("/email/confirmacao_cadastro.html")
				.parametro("usuario", event.getUsuario())
				.build();
		
		envioEmail.enviar(mensagem);
		
	}

}

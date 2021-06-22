package com.digitalSystems.extendsfood.domain.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.digitalSystems.extendsfood.domain.event.ConfirmarCadastradoEvent;
import com.digitalSystems.extendsfood.domain.service.EnvioEmailService;
import com.digitalSystems.extendsfood.domain.service.EnvioEmailService.Mensagem;

@Component
public class NotificarClienteAtivarContaListener {

	@Autowired
	private EnvioEmailService envioEmail;
	
	@TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
	public void aoCadastrarCliente(ConfirmarCadastradoEvent event) {
		
		var mensagem = Mensagem.builder()
				.assunto("Confirmação de Cadastro")
				.templatePath("/email/confirmacao_cadastro.html")
				.parametro("usuario", event.getUsuario())
				.build();
		
		envioEmail.enviar(mensagem);
		
	}

}

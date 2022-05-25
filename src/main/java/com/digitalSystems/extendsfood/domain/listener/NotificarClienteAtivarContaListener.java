package com.digitalSystems.extendsfood.domain.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.digitalSystems.extendsfood.domain.event.ConfirmarCadastradoEvent;
import com.digitalSystems.extendsfood.domain.model.Usuario;
import com.digitalSystems.extendsfood.domain.service.EnvioEmailService;
import com.digitalSystems.extendsfood.domain.service.EnvioEmailService.Mensagem;

@Component
public class NotificarClienteAtivarContaListener {

	@Autowired
	private EnvioEmailService envioEmail;
	
	@TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
	public void aoCadastrarCliente(ConfirmarCadastradoEvent event) {
		
		Usuario cliente = event.getUsuario();
		
		var mensagem = Mensagem.builder()
				.assunto("Confirmação de Cadastro")
				.templatePath("/email/confirmacao_cadastro.html")
				.parametro("usuario", cliente)
				.destinatario(cliente.getEmail())
				.build();
		
		envioEmail.enviar(mensagem);
		
	}

}

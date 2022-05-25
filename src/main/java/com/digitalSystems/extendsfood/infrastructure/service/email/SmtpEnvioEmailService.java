package com.digitalSystems.extendsfood.infrastructure.service.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.digitalSystems.extendsfood.core.email.EmailProperties;
import com.digitalSystems.extendsfood.domain.service.EnvioEmailService;

public class SmtpEnvioEmailService implements EnvioEmailService {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private EmailProperties emailProperties;
	
	@Autowired
	private ProcessadorEmailTemplate processadorEmailTemplate;

	@Override
	public void enviar(Mensagem mensagem) {
		try {
			MimeMessage mimeMessage = criarMimeMessage(mensagem);

			mailSender.send(mimeMessage);
		} catch (Exception e) {
			throw new EmailException("Não foi possível enviar e-mail", e);
		}
	}

	protected MimeMessage criarMimeMessage(Mensagem mensagem) throws MessagingException {
		
		String corpo = processadorEmailTemplate.processarTemplate(mensagem);

		// Representa a mensagem que queremos enviar por e-mail, configura o
		// destinatário, o corpo do e-mail
		MimeMessage mimeMessage = mailSender.createMimeMessage();

		// Ajuda nas configurações no MimeMessage (Classe Auxiliar)
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
		helper.setFrom(emailProperties.getRemetente());
		helper.setTo(mensagem.getDestinatarios().toArray(new String[0]));
		helper.setSubject(mensagem.getAssunto());
		helper.setText(corpo, true);// true especifica que o corpo deve ser em HTML

		return mimeMessage;

	}

}
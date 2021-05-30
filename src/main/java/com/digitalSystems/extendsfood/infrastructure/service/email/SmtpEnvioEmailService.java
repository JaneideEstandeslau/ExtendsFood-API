package com.digitalSystems.extendsfood.infrastructure.service.email;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.digitalSystems.extendsfood.core.email.EmailProperties;
import com.digitalSystems.extendsfood.core.email.OffsetDateTimeFormat;
import com.digitalSystems.extendsfood.domain.service.EnvioEmailService;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class SmtpEnvioEmailService implements EnvioEmailService {

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private EmailProperties emailProperties;
	
	@Autowired
	private Configuration freemarkerConfig;
	
	 @Autowired
	 private OffsetDateTimeFormat offsetDateTimeFormat;
	
	@Override
	public void enviar(Mensagem mensagem) {
		try {
			String corpo = processarTemplate(mensagem);
			
			//Representa a mensagem que queremos enviar por e-mail, configura o destinatário, o corpo do e-mail
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			
			//Ajuda nas configurações no MimeMessage (Classe Auxiliar)
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
			helper.setFrom(emailProperties.getRemetente());
			helper.setSubject(mensagem.getAssunto());
			helper.setText(corpo, true);//true especifica que o corpo deve ser em HTML
			
			//TODO mudar para muitos destinatarios em produção
			if(emailProperties.getDestinatario().isEmpty()) {
				helper.setTo(mensagem.getDestinatarios().toArray(new String[0]));
			}
			else {
				helper.setTo(emailProperties.getDestinatario());
			}
			
			mailSender.send(mimeMessage);
		} catch (Exception e) {
			throw new EmailException("Não foi possível enviar e-mail", e);
		}
	}
	
	protected String processarTemplate(Mensagem mensagem) {
		try {
			
			//Adiciona a formatação da Data
			freemarkerConfig.setSharedVariable("offsetDateTimeFormat", offsetDateTimeFormat);
			
			//Buscar o tamplate HTML no sistema
			Template template = freemarkerConfig.getTemplate(mensagem.getTemplatePath());
			
			//Processar o tamplate HTML e converter em uma String adiciona os valos passados no valores esperados
			return FreeMarkerTemplateUtils.processTemplateIntoString(
					template, mensagem.getParametros());
			
		} catch (Exception e) {
			throw new EmailException("Não foi possível montar o template do e-mail", e);
		}
	}


}
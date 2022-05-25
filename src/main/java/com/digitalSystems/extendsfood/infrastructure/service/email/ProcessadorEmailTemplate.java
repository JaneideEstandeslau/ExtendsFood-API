package com.digitalSystems.extendsfood.infrastructure.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.digitalSystems.extendsfood.core.email.OffsetDateTimeFormat;
import com.digitalSystems.extendsfood.domain.service.EnvioEmailService.Mensagem;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Component
public class ProcessadorEmailTemplate {
	
	@Autowired
	private Configuration freemarkerConfig;
	
	 @Autowired
	 private OffsetDateTimeFormat offsetDateTimeFormat;
	
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

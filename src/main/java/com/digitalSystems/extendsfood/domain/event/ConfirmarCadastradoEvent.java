package com.digitalSystems.extendsfood.domain.event;

import org.springframework.context.ApplicationEvent;

import com.digitalSystems.extendsfood.domain.model.Usuario;

import lombok.Getter;

@Getter
public class ConfirmarCadastradoEvent extends ApplicationEvent {
	
	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;

	public ConfirmarCadastradoEvent(Object source, Usuario usuario) {
		super(source);
		this.usuario = usuario;
	}
}

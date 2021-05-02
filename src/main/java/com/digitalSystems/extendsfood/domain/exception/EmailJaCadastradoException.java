package com.digitalSystems.extendsfood.domain.exception;

public class EmailJaCadastradoException extends NegocioException{

	
	private static final long serialVersionUID = 1L;

	public EmailJaCadastradoException(String mensagem) {
		super(mensagem);
	}
	
	public EmailJaCadastradoException() {
		super("Já existe um Usuário cadastrado no sistema com esse E-mail.");
	}


}

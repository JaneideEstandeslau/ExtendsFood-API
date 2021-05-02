package com.digitalSystems.extendsfood.domain.exception;

public class CpfJaCadastradoException extends NegocioException{

	
	private static final long serialVersionUID = 1L;

	public CpfJaCadastradoException(String mensagem) {
		super(mensagem);
	}
	
	public CpfJaCadastradoException() {
		super("Já existe um Usuário cadastrado no sistema com esse CPF.");
	}


}

package com.digitalSystems.extendsfood.domain.exception;

public class CNPJJaCadastradoException extends NegocioException{

	
	private static final long serialVersionUID = 1L;

	public CNPJJaCadastradoException(String mensagem) {
		super(mensagem);
	}
	
	public CNPJJaCadastradoException() {
		super("Já existe um restaurante cadastrado no sistema com esse CNPJ.");
	}

}

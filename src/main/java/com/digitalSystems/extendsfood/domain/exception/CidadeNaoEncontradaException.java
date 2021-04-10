package com.digitalSystems.extendsfood.domain.exception;

public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException{

	
	private static final long serialVersionUID = 1L;

	public CidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public CidadeNaoEncontradaException(Long cidadeId) {
		super(String.format("Não existe o cadastro de Cidade com o código %d", cidadeId));
	}

}

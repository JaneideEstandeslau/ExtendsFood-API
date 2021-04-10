package com.digitalSystems.extendsfood.domain.exception;

public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException{

	
	private static final long serialVersionUID = 1L;

	public CozinhaNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	public CozinhaNaoEncontradaException(Long cozinhaId) {
		super(String.format("Não existe o cadastro de Cozinha com o código %d", cozinhaId));
	}
}

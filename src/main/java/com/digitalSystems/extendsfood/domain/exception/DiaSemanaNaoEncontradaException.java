package com.digitalSystems.extendsfood.domain.exception;

public class DiaSemanaNaoEncontradaException extends EntidadeNaoEncontradaException{

	
	private static final long serialVersionUID = 1L;

	public DiaSemanaNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	public DiaSemanaNaoEncontradaException(Long cozinhaId) {
		super(String.format("Não existe o cadastro de Dia da Semana com o código %d", cozinhaId));
	}

}

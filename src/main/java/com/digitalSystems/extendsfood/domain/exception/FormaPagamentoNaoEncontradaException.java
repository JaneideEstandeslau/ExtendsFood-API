package com.digitalSystems.extendsfood.domain.exception;

public class FormaPagamentoNaoEncontradaException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;
	
	public FormaPagamentoNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	public FormaPagamentoNaoEncontradaException(Long restauranteId) {
		super(String.format("Não existe o cadastro de forma de pagamento com o código %d", restauranteId));
	}

}

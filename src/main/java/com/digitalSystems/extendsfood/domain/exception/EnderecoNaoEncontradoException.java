package com.digitalSystems.extendsfood.domain.exception;

public class EnderecoNaoEncontradoException extends EntidadeNaoEncontradaException{

	
	private static final long serialVersionUID = 1L;

	public EnderecoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public EnderecoNaoEncontradoException(Long restauranteId) {
		super(String.format("Não existe o cadastro de Endereço com o código %d", restauranteId));
	}


}

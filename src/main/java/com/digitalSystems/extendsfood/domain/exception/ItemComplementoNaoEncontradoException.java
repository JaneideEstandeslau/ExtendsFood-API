package com.digitalSystems.extendsfood.domain.exception;

public class ItemComplementoNaoEncontradoException extends EntidadeNaoEncontradaException{

	
	private static final long serialVersionUID = 1L;

	public ItemComplementoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public ItemComplementoNaoEncontradoException(Long cidadeId) {
		super(String.format("Não existe o cadastro de Item de Complemento com o código %d", cidadeId));
	}

}

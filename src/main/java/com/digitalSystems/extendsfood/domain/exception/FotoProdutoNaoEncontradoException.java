package com.digitalSystems.extendsfood.domain.exception;

public class FotoProdutoNaoEncontradoException extends EntidadeNaoEncontradaException{
	
	private static final long serialVersionUID = 1L;

	public FotoProdutoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public FotoProdutoNaoEncontradoException(Long produtoId) {
		super(String.format("Não existe o cadastro de Foto para o Produto com codigo %d", produtoId));
	}
	

}

package com.digitalSystems.extendsfood.domain.exception;

public class ProdutoNaoCadastradoException extends EntidadeNaoEncontradaException{

	
	private static final long serialVersionUID = 1L;

	public ProdutoNaoCadastradoException(String mensagem) {
		super(mensagem);
	}
	
	public ProdutoNaoCadastradoException(Long restauranteId) {
		super(String.format("Não existe o cadastro de Produto com o código %d", restauranteId));
	}

}

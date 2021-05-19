package com.digitalSystems.extendsfood.domain.exception;

public class CategoriaProdutoNaoEncontradoException extends EntidadeNaoEncontradaException{
	
	private static final long serialVersionUID = 1L;

	public CategoriaProdutoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public CategoriaProdutoNaoEncontradoException(Long categoriaProdutoId) {
		super(String.format("Não existe o cadastro de Categoria Produto com codigo %d", categoriaProdutoId));
	}
	
	public CategoriaProdutoNaoEncontradoException(Long categoriaProdutoId, Long restauranteId) {
		super(String.format("Não existe o cadastro de Categoria com codigo %d no Restaurantecom codigo %d", categoriaProdutoId, restauranteId));
	}
	

}

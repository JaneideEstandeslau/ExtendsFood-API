package com.digitalSystems.extendsfood.domain.exception;
public class ProdutoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public ProdutoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public ProdutoNaoEncontradoException(Long produtoId, Long categoriaId) {
		this(String.format("Não existe um cadastro de Produto com código %d na Categoria de código %d", 
				produtoId, categoriaId));
	}
	
}
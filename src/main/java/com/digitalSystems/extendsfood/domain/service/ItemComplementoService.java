package com.digitalSystems.extendsfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalSystems.extendsfood.domain.exception.CidadeNaoEncontradaException;
import com.digitalSystems.extendsfood.domain.model.ItemComplemento;
import com.digitalSystems.extendsfood.domain.repository.ProdutoRepository;

@Service
public class ItemComplementoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	public ItemComplemento buscarOuFalhar(Long itemComplementoId) {
		return produtoRepository.findByItemComplementoByid(itemComplementoId).orElseThrow(
				() -> new CidadeNaoEncontradaException(itemComplementoId));
	}
}

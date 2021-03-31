package com.digitalSystems.extendsfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.digitalSystems.extendsfood.domain.exception.EntidadeEmUsoException;
import com.digitalSystems.extendsfood.domain.exception.EntidadeNaoEncontradaException;
import com.digitalSystems.extendsfood.domain.model.CategoriaProduto;
import com.digitalSystems.extendsfood.domain.repository.CategoriaProdutoRepository;

@Service
public class CaregoriaProdutoService {

	@Autowired
	private CategoriaProdutoRepository categoriaRepository;
	
	public CategoriaProduto salvar(CategoriaProduto categoriaProduto) {
		return categoriaRepository.save(categoriaProduto);
	}
	
	public void excluir(Long categoriaProdutoId) {
		try {
			categoriaRepository.deleteById(categoriaProdutoId);
			
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
				String.format("Não existe um cadastro de categoria de produto com código %d", categoriaProdutoId));
		
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format("Cozinha de código %d não pode ser removida, pois está em uso", categoriaProdutoId));
		}
	}
}

package com.digitalSystems.extendsfood.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.digitalSystems.extendsfood.domain.exception.CidadeNaoEncontradaException;
import com.digitalSystems.extendsfood.domain.exception.EntidadeEmUsoException;
import com.digitalSystems.extendsfood.domain.model.Grupo;
import com.digitalSystems.extendsfood.domain.repository.GrupoRepository;

@Service
public class GrupoService {

	private static final String MSG_CIDADE_EM_USO = "grupo de código %d não pode ser removido, pois está em uso";
	
	@Autowired
	private GrupoRepository grupoRepository;	

	@Transactional
	public Grupo salvar(Grupo grupo) {
		return grupoRepository.save(grupo);
	}

	@Transactional
	public void excluir(Long grupoId) {
		try {
			grupoRepository.deleteById(grupoId);
			
			grupoRepository.flush();

		} catch (EmptyResultDataAccessException e) {
			throw new CidadeNaoEncontradaException(grupoId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_CIDADE_EM_USO, grupoId));
		}
	}

	public Grupo buscarOuFalhar(Long grupoId) {
		return grupoRepository.findById(grupoId).orElseThrow(
				() -> new CidadeNaoEncontradaException(grupoId));
	}

}

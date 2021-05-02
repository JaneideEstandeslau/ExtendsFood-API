package com.digitalSystems.extendsfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalSystems.extendsfood.domain.exception.PermissaoNaoEncontradaException;
import com.digitalSystems.extendsfood.domain.model.Permissao;
import com.digitalSystems.extendsfood.domain.repository.PermissaoRepository;

@Service
public class PermissaoService {

	@Autowired
	public PermissaoRepository permissaoRepository;
	
	public Permissao buscarOuFalhar(Long permissaoId) {
		return permissaoRepository.findById(permissaoId)
				.orElseThrow(() -> new PermissaoNaoEncontradaException(permissaoId));
	}
}

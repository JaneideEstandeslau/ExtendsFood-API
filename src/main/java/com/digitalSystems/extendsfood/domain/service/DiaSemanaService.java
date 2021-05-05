package com.digitalSystems.extendsfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalSystems.extendsfood.domain.exception.DiaSemanaNaoEncontradaException;
import com.digitalSystems.extendsfood.domain.model.DiaSemana;
import com.digitalSystems.extendsfood.domain.repository.DiaSemanaRepository;

@Service
public class DiaSemanaService {

	@Autowired
	private DiaSemanaRepository diaSemanaRepository;
	
	public DiaSemana buscarOuFalhar(Long diaSemanaId) {
		return diaSemanaRepository.findById(diaSemanaId)
				.orElseThrow(() -> new DiaSemanaNaoEncontradaException(diaSemanaId));
	}
}

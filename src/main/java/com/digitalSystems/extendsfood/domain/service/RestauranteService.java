package com.digitalSystems.extendsfood.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalSystems.extendsfood.domain.exception.CNPJJaCadastradoException;
import com.digitalSystems.extendsfood.domain.exception.RestauranteNaoEncontradoException;
import com.digitalSystems.extendsfood.domain.model.Cozinha;
import com.digitalSystems.extendsfood.domain.model.Restaurante;
import com.digitalSystems.extendsfood.domain.repository.RestauranteRepository;

@Service
public class RestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CozinhaService cozinhaService;

	@Transactional
	public Restaurante salvar(Restaurante restaurante) {

		validarCNPJ(restaurante);

		Long cozinhaId = restaurante.getCozinha().getId();

		Cozinha cozinha = cozinhaService.buscarOuFalhar(cozinhaId);

		restaurante.setCozinha(cozinha);

		return restauranteRepository.save(restaurante);

	}

	public Restaurante buscarOuFalhar(Long restauranteId) {
		return restauranteRepository.findById(restauranteId)
				.orElseThrow(() -> new RestauranteNaoEncontradoException(restauranteId));
	}

	private void validarCNPJ(Restaurante restaurante) {

		Restaurante restauranteMesmoCNPJ;

		if (restaurante.getId() == null) {
			restauranteMesmoCNPJ = restauranteRepository.findByCnpj(restaurante.getCnpj());
		} else {
			restauranteMesmoCNPJ = restauranteRepository.findByCnpjAndIdNot(restaurante.getCnpj(), restaurante.getId());
		}

		if (restauranteMesmoCNPJ != null) {
			throw new CNPJJaCadastradoException();
		}
	}
}

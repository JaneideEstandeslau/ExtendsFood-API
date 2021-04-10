package com.digitalSystems.extendsfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalSystems.extendsfood.domain.exception.RestauranteNaoEncontradoException;
import com.digitalSystems.extendsfood.domain.model.Cozinha;
import com.digitalSystems.extendsfood.domain.model.Restaurante;
import com.digitalSystems.extendsfood.domain.repository.RestauranteRepository;

@Service
public class RestauranteService {

	private static final String MSG_RESTAURANTE_NAO_ENCONTRADO = "Não existe um cadastro de restaurante com código %d";

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CozinhaService cozinhaService;

	public Restaurante salvar(Restaurante restaurante) {

		Long cozinhaId = restaurante.getCozinha().getId();

		Cozinha cozinha = cozinhaService.buscarOuFalhar(cozinhaId);

		restaurante.setCozinha(cozinha);
		return restauranteRepository.save(restaurante);
	}

	public Restaurante buscarOuFalhar(Long restauranteId) {
		return restauranteRepository.findById(restauranteId).orElseThrow(
				() -> new RestauranteNaoEncontradoException(restauranteId));
	}
}

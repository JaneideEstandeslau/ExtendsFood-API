package com.digitalSystems.extendsfood.domain.service;

import java.beans.Transient;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalSystems.extendsfood.domain.exception.CNPJJaCadastradoException;
import com.digitalSystems.extendsfood.domain.exception.RestauranteNaoEncontradoException;
import com.digitalSystems.extendsfood.domain.model.Cidade;
import com.digitalSystems.extendsfood.domain.model.Cozinha;
import com.digitalSystems.extendsfood.domain.model.Restaurante;
import com.digitalSystems.extendsfood.domain.repository.RestauranteRepository;

@Service
public class RestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CozinhaService cozinhaService;
	
	@Autowired
	private CidadeService cidadeService;

	@Transactional
	public Restaurante salvar(Restaurante restaurante) {

		restauranteRepository.detach(restaurante);
		
		validarCNPJ(restaurante);

		Long cozinhaId = restaurante.getCozinha().getId();
		Long cidadeId = restaurante.getEndereco().getCidade().getId();

		Cozinha cozinha = cozinhaService.buscarOuFalhar(cozinhaId);
		Cidade cidade = cidadeService.buscarOuFalhar(cidadeId);

		restaurante.setCozinha(cozinha);
		restaurante.getEndereco().setCidade(cidade);

		return restauranteRepository.save(restaurante);

	}

	@Transient
	public void ativar(Long restauranteId) {
		Restaurante restaurante = buscarOuFalhar(restauranteId);

		restaurante.ativar();
	}

	@Transient
	public void inativar(Long restauranteId) {
		Restaurante restaurante = buscarOuFalhar(restauranteId);

		restaurante.inativar();
	}

	public Restaurante buscarOuFalhar(Long restauranteId) {
		return restauranteRepository.findById(restauranteId)
				.orElseThrow(() -> new RestauranteNaoEncontradoException(restauranteId));
	}

	private void validarCNPJ(Restaurante restaurante) {

		Optional<Restaurante> restauranteExistente = restauranteRepository.findByCnpj(restaurante.getCnpj());

		if (restauranteExistente.isPresent() && !restauranteExistente.get().equals(restaurante)) {
			throw new CNPJJaCadastradoException();
		}
	}
}

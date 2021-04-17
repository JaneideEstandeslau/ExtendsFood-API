package com.digitalSystems.extendsfood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.digitalSystems.extendsfood.domain.exception.CidadeNaoEncontradaException;
import com.digitalSystems.extendsfood.domain.exception.CozinhaNaoEncontradaException;
import com.digitalSystems.extendsfood.domain.exception.NegocioException;
import com.digitalSystems.extendsfood.domain.model.Restaurante;
import com.digitalSystems.extendsfood.domain.repository.RestauranteRepository;
import com.digitalSystems.extendsfood.domain.service.RestauranteService;

@RestController
@RequestMapping(value = "/restaurante")
public class RestauranteController {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private RestauranteService restauranteService;

	@GetMapping
	public List<Restaurante> listar() {
		return restauranteRepository.findAll();
	}

	@GetMapping("/{restauranteId}")
	public Restaurante buscar(@PathVariable Long restauranteId) {
		return restauranteService.buscarOuFalhar(restauranteId);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Restaurante adicionar(@RequestBody @Valid Restaurante restaurante) {
		try {
			return restauranteService.salvar(restaurante);
			
		} catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@PutMapping("/{restauranteId}")
	public Restaurante atualizar(@PathVariable Long restauranteId, @RequestBody Restaurante restaurante) {

		try {
			Restaurante restauranteAtual = restauranteService.buscarOuFalhar(restauranteId);
	
			BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formasPagamento");

			return restauranteService.salvar(restauranteAtual);
			
		}catch(CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}

	}
}

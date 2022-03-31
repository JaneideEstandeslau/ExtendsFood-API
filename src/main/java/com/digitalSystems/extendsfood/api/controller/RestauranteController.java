package com.digitalSystems.extendsfood.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.digitalSystems.extendsfood.api.assembler.RestauranteBasicoModelAssembler;
import com.digitalSystems.extendsfood.api.assembler.RestauranteModelAssembler;
import com.digitalSystems.extendsfood.api.disassempler.RestauranteInputDisassembler;
import com.digitalSystems.extendsfood.api.model.RestauranteBasicoModel;
import com.digitalSystems.extendsfood.api.model.RestauranteModel;
import com.digitalSystems.extendsfood.api.model.inputEntidade.RestauranteInput;
import com.digitalSystems.extendsfood.api.openapi.controller.RestauranteControllerOpenApi;
import com.digitalSystems.extendsfood.core.security.CheckSecurity;
import com.digitalSystems.extendsfood.domain.exception.CidadeNaoEncontradaException;
import com.digitalSystems.extendsfood.domain.exception.CozinhaNaoEncontradaException;
import com.digitalSystems.extendsfood.domain.exception.NegocioException;
import com.digitalSystems.extendsfood.domain.model.Restaurante;
import com.digitalSystems.extendsfood.domain.repository.RestauranteRepository;
import com.digitalSystems.extendsfood.domain.service.RestauranteService;

@RestController
@RequestMapping(path = "/restaurantes", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteController implements RestauranteControllerOpenApi{

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private RestauranteModelAssembler restauranteAssembler;
	
	@Autowired
	private RestauranteBasicoModelAssembler restauranteBasicoAssembler;
	
	@Autowired
	private RestauranteInputDisassembler restauranteDisassembler;

	@CheckSecurity.Restaurantes.PodeConsultar
	@GetMapping
	public CollectionModel<RestauranteBasicoModel> listar() {
		return restauranteBasicoAssembler.toCollectionModel(restauranteRepository.findAll());
	}

	@CheckSecurity.Restaurantes.PodeConsultar
	@GetMapping("/{restauranteId}")
	public RestauranteModel buscar(@PathVariable Long restauranteId) {
		Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);
		
		return restauranteAssembler.toModel(restaurante);
	}

	@CheckSecurity.Restaurantes.PodeGerenciarCadastro
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RestauranteModel adicionar(@RequestBody @Valid RestauranteInput restauranteInput) {
		try {
			
			Restaurante restaurante = restauranteDisassembler.toDomainObject(restauranteInput);
			
			return  restauranteAssembler.toModel(restauranteService.salvar(restaurante));

		} catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@CheckSecurity.Restaurantes.PodeGerenciarCadastro
	@PutMapping("/{restauranteId}")
	public RestauranteModel atualizar(@PathVariable Long restauranteId, @RequestBody RestauranteInput restauranteInput) {

		try {
			Restaurante restauranteAtual = restauranteService.buscarOuFalhar(restauranteId);
	
			restauranteDisassembler.copyToDomainObject(restauranteInput, restauranteAtual);

			return restauranteAssembler.toModel(restauranteService.salvar(restauranteAtual));
			
		}catch(CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@CheckSecurity.Restaurantes.PodeGerenciarCadastro
	@PutMapping("{restauranteId}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> ativar(@PathVariable Long restauranteId) {
		restauranteService.ativar(restauranteId);
		
		return ResponseEntity.noContent().build();
	}
	
	@CheckSecurity.Restaurantes.PodeGerenciarCadastro
	@DeleteMapping("{restauranteId}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> inativar(@PathVariable Long restauranteId) {
		restauranteService.inativar(restauranteId);
		
		return ResponseEntity.noContent().build();
	}
	
	@CheckSecurity.Restaurantes.PodeGerenciarFuncionamento
	@PutMapping("{restauranteId}/abertura")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> abrirFuncionamento(@PathVariable Long restauranteId) {
		restauranteService.abrirFuncionamento(restauranteId);
		
		return ResponseEntity.noContent().build();
	}
	
	@CheckSecurity.Restaurantes.PodeGerenciarFuncionamento
	@PutMapping("{restauranteId}/fechamento")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> fecharFuncionamento(@PathVariable Long restauranteId) {
		restauranteService.fecharFuncionamento(restauranteId);
		
		return ResponseEntity.noContent().build();
	}
}

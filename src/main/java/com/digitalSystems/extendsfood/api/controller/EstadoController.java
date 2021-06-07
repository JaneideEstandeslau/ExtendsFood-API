package com.digitalSystems.extendsfood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.digitalSystems.extendsfood.api.assembler.EstadoModelAssembler;
import com.digitalSystems.extendsfood.api.disassempler.EstadoInputDisassembler;
import com.digitalSystems.extendsfood.api.model.EstadoModel;
import com.digitalSystems.extendsfood.api.model.inputEntidade.EstadoInput;
import com.digitalSystems.extendsfood.api.openapi.controller.EstadoControllerOpenApi;
import com.digitalSystems.extendsfood.domain.model.Estado;
import com.digitalSystems.extendsfood.domain.repository.EstadoRepository;
import com.digitalSystems.extendsfood.domain.service.EstadoService;

@RestController
@RequestMapping(path = "/estados", produces = MediaType.APPLICATION_JSON_VALUE)
public class EstadoController implements EstadoControllerOpenApi{

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private EstadoModelAssembler estadoAssembler;
	
	@Autowired
	private EstadoInputDisassembler estadoDisassembler;

	@GetMapping
	public List<EstadoModel> listar() {
		return estadoAssembler.toCollectionModel(estadoRepository.findAll());
	}

	@GetMapping("/{estadoId}")
	public EstadoModel buscar(@PathVariable Long estadoId) {
		Estado estado = estadoService.buscarOuFalhar(estadoId);
		
		return estadoAssembler.toModel(estado);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EstadoModel adicionar(@RequestBody @Valid EstadoInput estadoInput) {
		
		Estado  estado = estadoDisassembler.toDomainObject(estadoInput);
		
		return estadoAssembler.toModel(estadoService.salvar(estado));
	}

	@PutMapping("/{estadoId}")
	public EstadoModel atualizar(@PathVariable Long estadoId, @RequestBody @Valid EstadoInput estadoInput) {
		Estado estadoAtual = estadoService.buscarOuFalhar(estadoId);

		estadoDisassembler.copyToDomainObject(estadoInput, estadoAtual);

		return estadoAssembler.toModel(estadoService.salvar(estadoAtual));
	}

	@DeleteMapping("/{estadoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long estadoId) {
		estadoService.excluir(estadoId);

	}

}
package com.digitalSystems.extendsfood.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.digitalSystems.extendsfood.api.assembler.CozinhaModelAssembler;
import com.digitalSystems.extendsfood.api.disassempler.CozinhaInputDisassembler;
import com.digitalSystems.extendsfood.api.model.CozinhaModel;
import com.digitalSystems.extendsfood.api.model.inputEntidade.CozinhaInput;
import com.digitalSystems.extendsfood.api.openapi.controller.CozinhaControllerOpenApi;
import com.digitalSystems.extendsfood.core.security.CheckSecurity;
import com.digitalSystems.extendsfood.domain.model.Cozinha;
import com.digitalSystems.extendsfood.domain.repository.CozinhaRepository;
import com.digitalSystems.extendsfood.domain.service.CozinhaService;

@RestController
@RequestMapping(value = "/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaController implements CozinhaControllerOpenApi {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private CozinhaService cozinhaService;

	@Autowired
	private CozinhaModelAssembler cozinhaAssembler;

	@Autowired
	private CozinhaInputDisassembler cozinhaDesassembler;

	@CheckSecurity.Cozinhas.PodeConsultar
	@GetMapping
	public CollectionModel<CozinhaModel> listar() {
		return cozinhaAssembler.toCollectionModel(cozinhaRepository.findAll());
	}

	@CheckSecurity.Cozinhas.PodeConsultar
	@GetMapping("/{cozinhaId}")
	public CozinhaModel buscar(@PathVariable Long cozinhaId) {
		Cozinha cozinha = cozinhaService.buscarOuFalhar(cozinhaId);

		return cozinhaAssembler.toModel(cozinha);
	}

	@CheckSecurity.Cozinhas.PodeEditar
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CozinhaModel adicionar(@RequestBody @Valid CozinhaInput cozinhaInput) {
		Cozinha cozinha = cozinhaDesassembler.toDomainObject(cozinhaInput);

		return cozinhaAssembler.toModel(cozinhaService.salvar(cozinha));
	}

	@CheckSecurity.Cozinhas.PodeEditar
	@PutMapping("/{cozinhaId}")
	public CozinhaModel atualizar(@PathVariable Long cozinhaId, @RequestBody @Valid CozinhaInput cozinhaInput) {
		Cozinha cozinhaAtual = cozinhaService.buscarOuFalhar(cozinhaId);

		cozinhaDesassembler.copyToDomainObject(cozinhaInput, cozinhaAtual);

		return cozinhaAssembler.toModel(cozinhaService.salvar(cozinhaAtual));

	}

	@CheckSecurity.Cozinhas.PodeEditar
	@DeleteMapping("/{cozinhaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cozinhaId) {
		cozinhaService.excluir(cozinhaId);

	}
}

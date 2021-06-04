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

import com.digitalSystems.extendsfood.api.assembler.CidadeModelAssembler;
import com.digitalSystems.extendsfood.api.disassempler.CidadeInputDisassembler;
import com.digitalSystems.extendsfood.api.model.CidadeModel;
import com.digitalSystems.extendsfood.api.model.inputEntidade.CidadeInput;
import com.digitalSystems.extendsfood.api.openapi.controller.CidadeControllerOpenApi;
import com.digitalSystems.extendsfood.domain.exception.EstadoNaoEncontradoException;
import com.digitalSystems.extendsfood.domain.exception.NegocioException;
import com.digitalSystems.extendsfood.domain.model.Cidade;
import com.digitalSystems.extendsfood.domain.repository.CidadeRepository;
import com.digitalSystems.extendsfood.domain.service.CidadeService;

@RestController
@RequestMapping(path = "/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeController implements CidadeControllerOpenApi {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private CidadeModelAssembler cidadeAssembler;
	
	@Autowired
	private CidadeInputDisassembler cidadeDisassembler;

	@GetMapping
	public List<CidadeModel> listar() {
		return cidadeAssembler.toCollectionModel(cidadeRepository.findAll());
	}

	@GetMapping("/{cidadeId}")
	public CidadeModel buscar(@PathVariable Long cidadeId) {
		Cidade cidade = cidadeService.buscarOuFalhar(cidadeId);
		
		return cidadeAssembler.toModel(cidade);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeModel adicionar(@RequestBody @Valid CidadeInput cidadeInput) {
		try {
			
			Cidade cidade = cidadeDisassembler.toDomainObject(cidadeInput);
			
			return cidadeAssembler.toModel(cidadeService.salvar(cidade));
			
		}catch(EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@PutMapping("/{cidadeId}")
	public CidadeModel atualizar(@PathVariable Long cidadeId, @RequestBody @Valid CidadeInput cidadeInput) {

		try {
			Cidade cidadeAtual = cidadeService.buscarOuFalhar(cidadeId);
	
			cidadeDisassembler.copyToDomainObject(cidadeInput, cidadeAtual);

			return cidadeAssembler.toModel(cidadeService.salvar(cidadeAtual));
			
		}catch(EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}

	}

	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cidadeId) {
		
		cidadeService.excluir(cidadeId);

	}

}
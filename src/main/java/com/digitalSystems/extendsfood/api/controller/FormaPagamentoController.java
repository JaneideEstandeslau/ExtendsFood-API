package com.digitalSystems.extendsfood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.digitalSystems.extendsfood.api.assembler.FormaPagamentoModelAssembler;
import com.digitalSystems.extendsfood.api.disassempler.FormaPagamentoInputDisassembler;
import com.digitalSystems.extendsfood.api.model.FormaPagamentoModel;
import com.digitalSystems.extendsfood.api.model.inputEntidade.FormaPagamentoInput;
import com.digitalSystems.extendsfood.domain.model.FormaPagamento;
import com.digitalSystems.extendsfood.domain.repository.FormaPagamentoRepository;
import com.digitalSystems.extendsfood.domain.service.FormaPagamentoService;

@RestController
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController {

	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;

	@Autowired
	private FormaPagamentoService formaPagamentoService;
	
	@Autowired
	private FormaPagamentoModelAssembler formaPagamentoAssembler;
	
	@Autowired
	private FormaPagamentoInputDisassembler formaPagamentoDisassembler;

	@GetMapping
	public List<FormaPagamentoModel> listar() {
		return formaPagamentoAssembler.toCollectionModel(formaPagamentoRepository.findAll());
	}

	@GetMapping("/{formaPagamentoId}")
	public FormaPagamentoModel buscar(@PathVariable Long formaPagamentoId) {
		FormaPagamento formaPagamento = formaPagamentoService.buscarOuFalhar(formaPagamentoId);
		
		return formaPagamentoAssembler.toModel(formaPagamento);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public FormaPagamentoModel adicionar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
		
		FormaPagamento formaPagamento = formaPagamentoDisassembler.toDomainObject(formaPagamentoInput);
		
		return formaPagamentoAssembler.toModel(formaPagamentoService.salvar(formaPagamento));
	}

	@PutMapping("/{formaPagamentoId}")
	public FormaPagamentoModel atualizar(@PathVariable Long formaPagamentoId, @RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
		FormaPagamento formaPagamentoAtual = formaPagamentoService.buscarOuFalhar(formaPagamentoId);

		formaPagamentoDisassembler.copyToDomainObject(formaPagamentoInput, formaPagamentoAtual);

		return formaPagamentoAssembler.toModel(formaPagamentoService.salvar(formaPagamentoAtual));
	}

	@DeleteMapping("/{formaPagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long formaPagamentoId) {
		formaPagamentoService.excluir(formaPagamentoId);

	}
}

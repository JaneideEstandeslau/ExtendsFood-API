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

import com.digitalSystems.extendsfood.api.assembler.CategoriaProdutoModelAssembler;
import com.digitalSystems.extendsfood.api.disassempler.CategoriaProdutoInputDisassembler;
import com.digitalSystems.extendsfood.api.model.CategoriaProdutoModel;
import com.digitalSystems.extendsfood.api.model.inputEntidade.CategoriaProdutoInput;
import com.digitalSystems.extendsfood.domain.model.CategoriaProduto;
import com.digitalSystems.extendsfood.domain.repository.CategoriaProdutoRepository;
import com.digitalSystems.extendsfood.domain.service.CategoriaProdutoService;

@RestController
@RequestMapping(value = "/restaurantes/{restauranteId}/categorias")
public class CategoriaProdutoController {

	@Autowired
	private CategoriaProdutoRepository categoriaProdutoRepository;

	@Autowired
	private CategoriaProdutoService categoriaProdutoService;
	
	@Autowired
	private CategoriaProdutoModelAssembler categoriaProdutoAssembler;
	
	@Autowired
	private CategoriaProdutoInputDisassembler categoriaProdutoDisassembler;

	@GetMapping
	public List<CategoriaProdutoModel> listar(@PathVariable Long restauranteId) {
		return categoriaProdutoAssembler.toCollectionModel(
				categoriaProdutoService.buscarCategoriasRestarante(restauranteId));
	}

	@GetMapping("/{categoriaProdutoId}")
	public CategoriaProdutoModel buscar(@PathVariable Long restauranteId, @PathVariable Long categoriaProdutoId) {
		
		CategoriaProduto categoriaProduto = categoriaProdutoService.buscarOuFalhar(restauranteId, categoriaProdutoId);
		
		return categoriaProdutoAssembler.toModel(categoriaProduto);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CategoriaProdutoModel adicionar(@PathVariable Long restauranteId, 
			@RequestBody @Valid CategoriaProdutoInput categoriaProdutoInput) {
		
		CategoriaProduto categoriaProduto = categoriaProdutoDisassembler.toDomainObject(categoriaProdutoInput);
		
		return categoriaProdutoAssembler.toModel(categoriaProdutoService.salvar(categoriaProduto, restauranteId));
	}

	@PutMapping("/{categoriaProdutoId}")
	public CategoriaProdutoModel atualizar(@PathVariable Long restauranteId, @PathVariable Long categoriaProdutoId,
			@RequestBody @Valid CategoriaProdutoInput categoriaProdutoInput) {

		CategoriaProduto categoriaProdutoAtual = categoriaProdutoService.buscarOuFalhar(restauranteId, categoriaProdutoId);

		categoriaProdutoDisassembler.copyToDomainObject(categoriaProdutoInput, categoriaProdutoAtual);

		return  categoriaProdutoAssembler.toModel(categoriaProdutoService.salvar(categoriaProdutoAtual, restauranteId));

	}

	@DeleteMapping("/{categoriaProdutoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long categoriaProdutoId) {
		categoriaProdutoService.excluir(categoriaProdutoId);			
	}
}

package com.digitalSystems.extendsfood.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
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

import com.digitalSystems.extendsfood.api.assembler.CategoriaProdutoModelAssembler;
import com.digitalSystems.extendsfood.api.assembler.CategoriaProdutoResumoModelAssembler;
import com.digitalSystems.extendsfood.api.disassempler.CategoriaProdutoInputDisassembler;
import com.digitalSystems.extendsfood.api.model.CategoriaProdutoModel;
import com.digitalSystems.extendsfood.api.model.CategoriaProdutoResumoModel;
import com.digitalSystems.extendsfood.api.model.inputEntidade.CategoriaProdutoInput;
import com.digitalSystems.extendsfood.api.openapi.controller.CategoriaProdutoControllerOpenApi;
import com.digitalSystems.extendsfood.core.security.CheckSecurity;
import com.digitalSystems.extendsfood.domain.model.CategoriaProduto;
import com.digitalSystems.extendsfood.domain.repository.CategoriaProdutoRepository;
import com.digitalSystems.extendsfood.domain.service.CategoriaProdutoService;
import com.digitalSystems.extendsfood.infrastructure.spec.CategoriaProdutoSpecs;

@RestController
@RequestMapping(path = "/restaurantes/{restauranteId}/categorias", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoriaProdutoController implements CategoriaProdutoControllerOpenApi{

	@Autowired
	private CategoriaProdutoRepository categoriaProdutoRepository;
	
	@Autowired
	private CategoriaProdutoService categoriaProdutoService;
	
	@Autowired
	private CategoriaProdutoModelAssembler categoriaProdutoAssembler;
	
	@Autowired
	private CategoriaProdutoResumoModelAssembler categoriaProdutoResumoAssembler;
	
	@Autowired
	private CategoriaProdutoInputDisassembler categoriaProdutoDisassembler;
	
	@Autowired
	private PagedResourcesAssembler<CategoriaProduto> pagedResourcesAssembler;

	@CheckSecurity.Restaurantes.PodeConsultar
	@GetMapping
	public PagedModel<CategoriaProdutoResumoModel> listar(@PathVariable Long restauranteId,
			@PageableDefault(size = 10) Pageable pageable) {

		Page<CategoriaProduto> categoriasPage = categoriaProdutoRepository
				.findAll(CategoriaProdutoSpecs.peginarCategoriasDosProdutos(restauranteId), pageable);
		
		PagedModel<CategoriaProdutoResumoModel> categoriasPagedModel = pagedResourcesAssembler
				.toModel(categoriasPage, categoriaProdutoResumoAssembler);

		return categoriasPagedModel;
		
	}
	
	@CheckSecurity.Restaurantes.PodeConsultar
	@GetMapping("/{categoriaProdutoId}")
	public CategoriaProdutoModel buscar(@PathVariable Long restauranteId, @PathVariable Long categoriaProdutoId) {
		
		CategoriaProduto categoriaProduto = categoriaProdutoService.buscarOuFalhar(restauranteId, categoriaProdutoId);
		
		return categoriaProdutoAssembler.toModel(categoriaProduto);
	}

	@CheckSecurity.Restaurantes.PodeGerenciarCadastro
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CategoriaProdutoResumoModel adicionar(@PathVariable Long restauranteId, 
			@RequestBody @Valid CategoriaProdutoInput categoriaProdutoInput) {
		
		CategoriaProduto categoriaProduto = categoriaProdutoDisassembler.toDomainObject(categoriaProdutoInput);
		
		return categoriaProdutoResumoAssembler.toModel(categoriaProdutoService.salvar(categoriaProduto, restauranteId));
	}

	@CheckSecurity.Restaurantes.PodeGerenciarCadastro
	@PutMapping("/{categoriaProdutoId}")
	public CategoriaProdutoResumoModel atualizar(@PathVariable Long restauranteId, @PathVariable Long categoriaProdutoId,
			@RequestBody @Valid CategoriaProdutoInput categoriaProdutoInput) {

		CategoriaProduto categoriaProdutoAtual = categoriaProdutoService.buscarOuFalhar(restauranteId, categoriaProdutoId);

		categoriaProdutoDisassembler.copyToDomainObject(categoriaProdutoInput, categoriaProdutoAtual);

		return  categoriaProdutoResumoAssembler.toModel(categoriaProdutoService.salvar(categoriaProdutoAtual, restauranteId));

	}

	@CheckSecurity.Restaurantes.PodeGerenciarCadastro
	@DeleteMapping("/{categoriaProdutoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long restauranteId, @PathVariable Long categoriaProdutoId) {
		categoriaProdutoService.excluir(restauranteId, categoriaProdutoId);			
	}
}

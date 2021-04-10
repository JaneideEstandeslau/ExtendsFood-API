package com.digitalSystems.extendsfood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.digitalSystems.extendsfood.domain.exception.EntidadeEmUsoException;
import com.digitalSystems.extendsfood.domain.exception.EntidadeNaoEncontradaException;
import com.digitalSystems.extendsfood.domain.model.CategoriaProduto;
import com.digitalSystems.extendsfood.domain.repository.CategoriaProdutoRepository;
import com.digitalSystems.extendsfood.domain.service.CaregoriaProdutoService;

@RestController
@RequestMapping(value = "/categorias-produto")
public class CategoriaProdutoController {

	@Autowired
	private CategoriaProdutoRepository categoriaProdutoRepository;

	@Autowired
	private CaregoriaProdutoService categoriaProdutoService;

	@GetMapping
	public List<CategoriaProduto> listar() {
		return categoriaProdutoRepository.findAll();
	}

	@GetMapping("/{cozinhaId}")
	public CategoriaProduto buscar(@PathVariable Long categoriaProdutoId) {
		return categoriaProdutoService.buscarOuFalhar(categoriaProdutoId);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CategoriaProduto adicionar(@RequestBody CategoriaProduto categoriaProduto) {
		return categoriaProdutoService.salvar(categoriaProduto);
	}

	@PutMapping("/{cozinhaId}")
	public CategoriaProduto atualizar(@PathVariable Long categoriaProdutoId,
			@RequestBody CategoriaProduto categoriaProduto) {

		CategoriaProduto categoriaProdutoAtual = categoriaProdutoService.buscarOuFalhar(categoriaProdutoId);

		BeanUtils.copyProperties(categoriaProduto, categoriaProdutoAtual, "id");

		return categoriaProdutoService.salvar(categoriaProdutoAtual);

	}

	@DeleteMapping("/{cozinhaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long categoriaProdutoId) {
		categoriaProdutoService.excluir(categoriaProdutoId);			
	}
}

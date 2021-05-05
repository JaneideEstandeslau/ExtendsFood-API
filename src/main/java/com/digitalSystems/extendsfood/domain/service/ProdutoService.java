package com.digitalSystems.extendsfood.domain.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalSystems.extendsfood.domain.exception.ProdutoNaoCadastradoException;
import com.digitalSystems.extendsfood.domain.model.CategoriaProduto;
import com.digitalSystems.extendsfood.domain.model.DiaSemana;
import com.digitalSystems.extendsfood.domain.model.Produto;
import com.digitalSystems.extendsfood.domain.model.Restaurante;
import com.digitalSystems.extendsfood.domain.repository.ProdutoRepository;

@Service
public class ProdutoService {

	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private CategoriaProdutoService categoriaService;
	
	@Autowired
	private DiaSemanaService disSemanaService;
	
	@Transactional
	public Produto salvar(Produto produto, Long restauranteId) {
		
		validarProduto(produto, restauranteId);
		
		return produtoRepository.save(produto);
	}
	
	public List<Produto> buscarProdutosDoRestaurante(Long restauranteId){
		Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);
		
		return produtoRepository.findByRestaurante(restaurante);
	}
	
	public Produto buscarProdutoDoRestaurante(Long restauranteId, Long produtoId) {
		
		Produto produto = buscarOuFalhar(produtoId);
		Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);
		
		return produtoRepository.findById(restaurante.getId(), produto.getId()).get();
		
	}
	
	public Produto buscarOuFalhar(Long produtoId) {
		return produtoRepository.findById(produtoId)
				.orElseThrow(() -> new ProdutoNaoCadastradoException(produtoId));
	}
	
	public void validarProduto(Produto produto, Long restauranteId) {
		
		Set<DiaSemana> diasDisponiveis = new HashSet<>();
		
		Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);
		CategoriaProduto categoriaProduto = categoriaService.buscarOuFalhar(produto.getCategoriaProduto().getId());
		
		produto.setRestaurante(restaurante);
		produto.setCategoriaProduto(categoriaProduto);
		
		produto.getComplementos().forEach(complemento -> {
			complemento.setProduto(produto);
			
			complemento.getItens().forEach(item -> {
				item.setComplemento(complemento);
			});
		});
		
		produto.getDiasDisponiveis().forEach(dia -> {
			
			DiaSemana diaSemana = disSemanaService.buscarOuFalhar(dia.getId());
			
			diasDisponiveis.add(diaSemana);
		});
		
		produto.setDiasDisponiveis(diasDisponiveis);
	}
}

package com.digitalSystems.extendsfood.domain.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalSystems.extendsfood.domain.exception.ProdutoNaoEncontradoException;
import com.digitalSystems.extendsfood.domain.model.CategoriaProduto;
import com.digitalSystems.extendsfood.domain.model.DiaSemana;
import com.digitalSystems.extendsfood.domain.model.Produto;
import com.digitalSystems.extendsfood.domain.model.Restaurante;
import com.digitalSystems.extendsfood.domain.repository.ProdutoRepository;

@Service
public class ProdutoService {

	private static final String PRODUTO_NAO_ENCONTRADO = "Não existe um cadastro de Produto com o código %d no Restestaurante de código %d";
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaProdutoService categoriaService;
	
	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private DiaSemanaService disSemanaService;
	
	@Transactional
	public Produto salvar(Produto produto, Long categoriaId, Long restauranteId) {
		
		validarProduto(produto, categoriaId, restauranteId);
		
		return produtoRepository.save(produto);
	}
	
	/**
	 * Busca todos os produtos da categoria do restaurante
	 * @param restauranteId
	 * @param categoriaId
	 * @return
	 */
	public List<Produto> buscarTodosProdutosCategoriaRestaurante(Long restauranteId, Long categoriaId){
		
		Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);
		CategoriaProduto categoria = categoriaService.buscarOuFalhar(restaurante.getId(), categoriaId);
		
		return produtoRepository.findByAtivoByCategoriaByRestaurante(categoria, restaurante);
	}
	
	/**
	 * Busca um produto específico da categoria do restaurante
	 * @param restauranteId
	 * @param categoriaId
	 * @param produtoId
	 * @return
	 */
	public Produto buscarOuFalhar(Long restauranteId, Long categoriaId, Long produtoId) {
		
		Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);
		CategoriaProduto categoria = categoriaService.buscarOuFalhar(restaurante.getId(), categoriaId);
		
		return produtoRepository.findById(produtoId, categoria.getId(), restaurante.getId())
				.orElseThrow(() -> new ProdutoNaoEncontradoException(produtoId, categoriaId));
		
	}
	
	public Produto buscarProdutoDoRestaurante(Long restauranteId, Long produtoId) {
		
		Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);
		
		return produtoRepository.findByRestaurante(restaurante.getId(), produtoId)
				.orElseThrow(() -> new ProdutoNaoEncontradoException(
						String.format(PRODUTO_NAO_ENCONTRADO, produtoId, restauranteId)));
		
	}
	
	public void validarProduto(Produto produto, Long categoriaId, Long restauranteId) {
		
		Set<DiaSemana> diasDisponiveis = new HashSet<>();
		
		Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);
		CategoriaProduto categoriaProduto = categoriaService.buscarOuFalhar(restauranteId, categoriaId);
		
		categoriaProduto.setRestaurante(restaurante);
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

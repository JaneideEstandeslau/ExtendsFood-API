package com.digitalSystems.extendsfood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.digitalSystems.extendsfood.api.ExtendsLinks;
import com.digitalSystems.extendsfood.api.controller.RestauranteCategoriaProdutoController;
import com.digitalSystems.extendsfood.api.model.ProdutoModel;
import com.digitalSystems.extendsfood.domain.model.Produto;

@Component
public class ProdutoModelAssembler extends RepresentationModelAssemblerSupport<Produto, ProdutoModel>{

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ExtendsLinks extendsLinks;

	public ProdutoModelAssembler() {
		super(RestauranteCategoriaProdutoController.class, ProdutoModel.class);
	}
	
	public ProdutoModel toModel(Produto produto) {
		
		ProdutoModel produtoModel = createModelWithId(produto.getId(), produto, 
				produto.getCategoriaProduto().getRestaurante().getId(), produto.getCategoriaProduto().getId());
				
		modelMapper.map(produto, produtoModel);
        
        produtoModel.add(extendsLinks.linkToRestauranteCategoria(produto.getCategoriaProduto().getRestaurante().getId(),
        		produto.getCategoriaProduto().getId(), "produtos"));
        
        produtoModel.add(extendsLinks.linkToFotoProduto(produtoModel.getCategoriaProduto().getRestaurante().getId(),
        		produtoModel.getCategoriaProduto().getId(), produtoModel.getId(), "foto"));
        
        return produtoModel;
	}
	
	public List<ProdutoModel> toCollectionModel(List<Produto> produtos) {
		return produtos.stream()
				.map(produto -> toModel(produto))
				.collect(Collectors.toList());
	}
}

package com.digitalSystems.extendsfood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.digitalSystems.extendsfood.api.model.CategoriaProdutoModel;
import com.digitalSystems.extendsfood.api.model.CategoriaProdutoResumoModel;
import com.digitalSystems.extendsfood.domain.model.CategoriaProduto;

@Component
public class CategoriaProdutoModelAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public CategoriaProdutoModel toModel(CategoriaProduto categoriaProduto) {
		return modelMapper.map(categoriaProduto, CategoriaProdutoModel.class);
	}
	
	public CategoriaProdutoResumoModel toModelResumo(CategoriaProduto categoriaProduto) {
		return modelMapper.map(categoriaProduto, CategoriaProdutoResumoModel.class);
	}
	
	public List<CategoriaProdutoResumoModel> toCollectionModel(List<CategoriaProduto> categorias){
		return categorias.stream()
				.map(categoriaProduto -> toModelResumo(categoriaProduto))
				.collect(Collectors.toList());
	}
}

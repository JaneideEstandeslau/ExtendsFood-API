package com.digitalSystems.extendsfood.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.digitalSystems.extendsfood.api.controller.CategoriaProdutoController;
import com.digitalSystems.extendsfood.api.model.CategoriaProdutoResumoModel;
import com.digitalSystems.extendsfood.domain.model.CategoriaProduto;

@Component
public class CategoriaProdutoResumoModelAssembler 
	extends RepresentationModelAssemblerSupport<CategoriaProduto, CategoriaProdutoResumoModel>{

	@Autowired
	private ModelMapper modelMapper;
	
	public CategoriaProdutoResumoModelAssembler() {
		super(CategoriaProdutoController.class, CategoriaProdutoResumoModel.class);
	}

	@Override
	public CategoriaProdutoResumoModel toModel(CategoriaProduto categoriaProduto) {

		CategoriaProdutoResumoModel categoriaProdutoResumoModel = modelMapper.map(categoriaProduto,
				CategoriaProdutoResumoModel.class);
		
//		categoriaProdutoResumoModel.add(linkTo(CategoriaProdutoController.class).withRel("categorias"));
		
		return categoriaProdutoResumoModel;
	}
	
//	public List<CategoriaProdutoResumoModel> toCollectionModel(List<CategoriaProduto> categorias){
//		return categorias.stream()
//				.map(categoriaProduto -> toModelResumo(categoriaProduto))
//				.collect(Collectors.toList());
//	}
}

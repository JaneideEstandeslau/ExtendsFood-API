package com.digitalSystems.extendsfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.digitalSystems.extendsfood.api.ExtendsLinks;
import com.digitalSystems.extendsfood.api.controller.CategoriaProdutoController;
import com.digitalSystems.extendsfood.api.model.CategoriaProdutoResumoModel;
import com.digitalSystems.extendsfood.domain.model.CategoriaProduto;

@Component
public class CategoriaProdutoResumoModelAssembler 
	extends RepresentationModelAssemblerSupport<CategoriaProduto, CategoriaProdutoResumoModel>{

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ExtendsLinks extendsLinks;
	
	public CategoriaProdutoResumoModelAssembler() {
		super(CategoriaProdutoController.class, CategoriaProdutoResumoModel.class);
	}

	@Override
	public CategoriaProdutoResumoModel toModel(CategoriaProduto categoriaProduto) {

		CategoriaProdutoResumoModel categoriaProdutoModel = createModelWithId(categoriaProduto.getId(), categoriaProduto,
				categoriaProduto.getRestaurante().getId());

		modelMapper.map(categoriaProduto, categoriaProdutoModel);
		
		categoriaProdutoModel.getRestaurante().add(extendsLinks.linkToRestaurante(
				categoriaProduto.getRestaurante().getId()));
		
		return categoriaProdutoModel;
	}
	
//	public List<CategoriaProdutoResumoModel> toCollectionModel(List<CategoriaProduto> categorias){
//		return categorias.stream()
//				.map(categoriaProduto -> toModelResumo(categoriaProduto))
//				.collect(Collectors.toList());
//	}
}

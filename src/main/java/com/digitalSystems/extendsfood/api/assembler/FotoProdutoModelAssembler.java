package com.digitalSystems.extendsfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.digitalSystems.extendsfood.api.ExtendsLinks;
import com.digitalSystems.extendsfood.api.controller.RestauranteProdutoFotoController;
import com.digitalSystems.extendsfood.api.model.FotoProdutoModel;
import com.digitalSystems.extendsfood.domain.model.FotoProduto;

@Component
public class FotoProdutoModelAssembler
		extends RepresentationModelAssemblerSupport<FotoProduto, FotoProdutoModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ExtendsLinks extendsLinks;
	
	public FotoProdutoModelAssembler() {
		super(RestauranteProdutoFotoController.class, FotoProdutoModel.class);
	}

	public FotoProdutoModel toModel(FotoProduto foto) {
		FotoProdutoModel fotoProdutoModel = modelMapper.map(foto, FotoProdutoModel.class);
        
        fotoProdutoModel.add(extendsLinks.linkToFotoProduto(
                foto.getRestauranteId(), foto.getCategoriaId(), foto.getProduto().getId()));
        
        fotoProdutoModel.add(extendsLinks.linkToProduto(
                foto.getRestauranteId(), foto.getCategoriaId(), foto.getProduto().getId(), "produto"));
        
        return fotoProdutoModel;
	}

}
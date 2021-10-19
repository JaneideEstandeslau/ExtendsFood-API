package com.digitalSystems.extendsfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.digitalSystems.extendsfood.api.ExtendsLinks;
import com.digitalSystems.extendsfood.api.controller.GrupoPermissaoController;
import com.digitalSystems.extendsfood.api.model.PermissaoModel;
import com.digitalSystems.extendsfood.domain.model.Permissao;

@Component
public class PermissaoModelAssembler extends RepresentationModelAssemblerSupport<Permissao, PermissaoModel>{

	@Autowired
	private ExtendsLinks extendsLinks;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	public PermissaoModelAssembler() {
		super(GrupoPermissaoController.class, PermissaoModel.class);
	}
	
	public PermissaoModel toModel(Permissao permissao) {
		return modelMapper.map(permissao, PermissaoModel.class);
	}
	
	@Override
    public CollectionModel<PermissaoModel> toCollectionModel(Iterable<? extends Permissao> entities) {
        return super.toCollectionModel(entities);
    }   
}

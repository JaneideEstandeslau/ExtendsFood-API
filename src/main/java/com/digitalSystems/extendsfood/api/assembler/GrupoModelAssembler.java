package com.digitalSystems.extendsfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.digitalSystems.extendsfood.api.ExtendsLinks;
import com.digitalSystems.extendsfood.api.controller.GrupoController;
import com.digitalSystems.extendsfood.api.model.GrupoModel;
import com.digitalSystems.extendsfood.domain.model.Grupo;

@Component
public class GrupoModelAssembler extends RepresentationModelAssemblerSupport<Grupo, GrupoModel>{

	@Autowired
	private ExtendsLinks extendsLinks;

	@Autowired
	private ModelMapper modelMapper;
	
	public GrupoModelAssembler() {
		super(GrupoController.class, GrupoModel.class);
	}
	
	public GrupoModel toModel(Grupo grupo) {
		
		GrupoModel grupoModel = createModelWithId(grupo.getId(), grupo);
		
		modelMapper.map(grupo, grupoModel);
		
		grupoModel.add(extendsLinks.linkToGrupos("grupos"));
		grupoModel.add(extendsLinks.linkToGrupoPermissao(grupoModel.getId(), "permissoes"));
		
		return grupoModel;
	}
	
	@Override
	public CollectionModel<GrupoModel> toCollectionModel(Iterable<? extends Grupo> entities) {
		return super.toCollectionModel(entities)
				.add(extendsLinks.linkToGrupos());
	}
}

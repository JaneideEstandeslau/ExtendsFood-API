package com.digitalSystems.extendsfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.digitalSystems.extendsfood.api.ExtendsLinks;
import com.digitalSystems.extendsfood.api.controller.CozinhaController;
import com.digitalSystems.extendsfood.api.model.CozinhaModel;
import com.digitalSystems.extendsfood.domain.model.Cozinha;

@Component
public class CozinhaModelAssembler extends RepresentationModelAssemblerSupport<Cozinha, CozinhaModel>{
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ExtendsLinks extendsLinks;
	
	public CozinhaModelAssembler() {
		super(CozinhaController.class, CozinhaModel.class);
	}

	@Override
	public CozinhaModel toModel(Cozinha cozinha) {
		CozinhaModel cozinhaModel = createModelWithId(cozinha.getId(), cozinha);
		modelMapper.map(cozinha, cozinhaModel);

		cozinhaModel.add(extendsLinks.linkToCozinhas("cozinhas"));

		return cozinhaModel;
	}

	
	@Override
	public CollectionModel<CozinhaModel> toCollectionModel(Iterable<? extends Cozinha> entities) {
		return super.toCollectionModel(entities)
				.add(extendsLinks.linkToCozinhas());
	}
}

package com.digitalSystems.extendsfood.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.digitalSystems.extendsfood.api.controller.CidadeController;
import com.digitalSystems.extendsfood.api.controller.EstadoController;
import com.digitalSystems.extendsfood.api.model.CidadeModel;
import com.digitalSystems.extendsfood.domain.model.Cidade;

@Component
public class CidadeModelAssembler 
				extends RepresentationModelAssemblerSupport<Cidade, CidadeModel>{//Classe que da suporte a montagem de Representation models
	
	@Autowired
	private ModelMapper modelMapper;
	
	public CidadeModelAssembler() {
		super(CidadeController.class, CidadeModel.class);
	}


	@Override
	public CidadeModel toModel(Cidade cidade) {
		
		//métodos que permitem criar uma instância do recurso e ter um LINK com um rel de self adicionado a ela
		CidadeModel cidadeModel = createModelWithId(cidade.getId(), cidade);
		
		modelMapper.map(cidade, cidadeModel);
		
		//Adiciona Hypermedia		
		//Cria uma chamada fictícia do método para gerar o LINK de acesso a esse método.
		cidadeModel.add(linkTo(methodOn(CidadeController.class)
				.listar()).withRel("cidades"));
		
		cidadeModel.getEstado().add(linkTo(methodOn(EstadoController.class)
				.buscar(cidadeModel.getEstado().getId())).withSelfRel());
		
		return cidadeModel;
	}
	
	@Override
	public CollectionModel<CidadeModel> toCollectionModel(Iterable<? extends Cidade> entities) {
		return super.toCollectionModel(entities)
				.add(linkTo(CidadeController.class).withSelfRel());
	}
	
	
//	public List<CidadeModel> toCollectionModel(List<Cidade> cidades){
//		return cidades.stream()
//				.map(cidade -> toModel(cidade))
//				.collect(Collectors.toList());
//	}
}

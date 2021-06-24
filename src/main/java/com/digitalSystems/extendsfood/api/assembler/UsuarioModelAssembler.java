package com.digitalSystems.extendsfood.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.lang.ModuleLayer.Controller;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.digitalSystems.extendsfood.api.controller.UsuarioController;
import com.digitalSystems.extendsfood.api.controller.UsuarioGrupoController;
import com.digitalSystems.extendsfood.api.model.UsuarioModel;
import com.digitalSystems.extendsfood.domain.model.Usuario;

@Component
public class UsuarioModelAssembler extends RepresentationModelAssemblerSupport<Usuario, UsuarioModel>{
	
	@Autowired
	private ModelMapper modelMapper;

	public UsuarioModelAssembler() {
		super(UsuarioController.class, UsuarioModel.class);
	}

	@Override
	public UsuarioModel toModel(Usuario usuario) {
		
		//métodos que permitem criar uma instância do recurso e ter um LINK com um rel de self adicionado a ela
		UsuarioModel usuarioModel = createModelWithId(usuario.getId(), usuario);
		
		modelMapper.map(usuario, usuarioModel);
		
		//Adiciona Hypermedia		
				//Cria uma chamada fictícia do método para gerar o LINK de acesso a esse método.
		usuarioModel.add(linkTo(methodOn(UsuarioController.class)
				.listar()).withRel("usuarios"));
		
		usuarioModel.add(linkTo(methodOn(UsuarioGrupoController.class)
				.listar(usuarioModel.getId())).withRel("grupos-usuario"));
		
		return usuarioModel;
	}
	
	@Override
	public CollectionModel<UsuarioModel> toCollectionModel(Iterable<? extends Usuario> entities) {
		return super.toCollectionModel(entities)
					.add(linkTo(UsuarioController.class).withSelfRel());
	}
	
//	public List<UsuarioModel> toCollectionModel(Collection<Usuario> usuarios) {
//		return usuarios.stream()
//				.map(usuario -> toModel(usuario))
//				.collect(Collectors.toList());
//	}
}

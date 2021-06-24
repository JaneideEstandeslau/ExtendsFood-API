package com.digitalSystems.extendsfood.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.digitalSystems.extendsfood.api.controller.UsuarioController;
import com.digitalSystems.extendsfood.api.controller.UsuarioGrupoController;
import com.digitalSystems.extendsfood.api.model.UsuarioEnderecoModel;
import com.digitalSystems.extendsfood.domain.model.Usuario;

@Component
public class UsuarioEnderecoModelAssembler extends RepresentationModelAssemblerSupport<Usuario, UsuarioEnderecoModel>{

	@Autowired
	private ModelMapper modelMapper;
	
	public UsuarioEnderecoModelAssembler() {
		super(UsuarioController.class, UsuarioEnderecoModel.class);
	}

	
	public UsuarioEnderecoModel toModel(Usuario usuario) {
		
		UsuarioEnderecoModel usuarioEnderecoModel = modelMapper.map(usuario, UsuarioEnderecoModel.class);
		
		usuarioEnderecoModel.add(linkTo(methodOn(UsuarioController.class)
				.buscar(usuarioEnderecoModel.getId())).withSelfRel());
		
		usuarioEnderecoModel.add(linkTo(methodOn(UsuarioController.class)
				.listar()).withRel("usuarios"));
		
		usuarioEnderecoModel.add(linkTo(methodOn(UsuarioGrupoController.class)
				.listar(usuarioEnderecoModel.getId())).withRel("grupos-usuario"));
		
		return usuarioEnderecoModel;
	}
}

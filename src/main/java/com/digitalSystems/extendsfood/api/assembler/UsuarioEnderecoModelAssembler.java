package com.digitalSystems.extendsfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.digitalSystems.extendsfood.api.model.UsuarioEnderecoModel;
import com.digitalSystems.extendsfood.domain.model.Usuario;

@Component
public class UsuarioEnderecoModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public UsuarioEnderecoModel toModel(Usuario usuario) {
		return modelMapper.map(usuario, UsuarioEnderecoModel.class);
	}
}

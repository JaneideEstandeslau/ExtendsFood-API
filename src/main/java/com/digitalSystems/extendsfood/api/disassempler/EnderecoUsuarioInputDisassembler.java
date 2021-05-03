package com.digitalSystems.extendsfood.api.disassempler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.digitalSystems.extendsfood.api.model.inputEntidade.EnderecoUsuarioInput;
import com.digitalSystems.extendsfood.domain.model.Endereco;

@Component
public class EnderecoUsuarioInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Endereco toDomainObject(EnderecoUsuarioInput enderecoUsuarioInput) {
		return modelMapper.map(enderecoUsuarioInput, Endereco.class);
	}
	
}

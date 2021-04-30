package com.digitalSystems.extendsfood.api.disassempler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.digitalSystems.extendsfood.api.model.inputEntidade.CidadeInput;
import com.digitalSystems.extendsfood.domain.model.Cidade;

@Component
public class CidadeInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Cidade toDomainObject(CidadeInput cidadeInput) {
		return modelMapper.map(cidadeInput, Cidade.class);
	}
	
	public void copyToDomainObject(CidadeInput cidadeInput, Cidade cidade) {
		modelMapper.map(cidadeInput, cidade);
	}
}

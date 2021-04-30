package com.digitalSystems.extendsfood.api.disassempler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.digitalSystems.extendsfood.api.model.inputEntidade.CategoriaProdutoInput;
import com.digitalSystems.extendsfood.domain.model.CategoriaProduto;

@Component
public class CategoriaProdutoInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public CategoriaProduto toDomainObject(CategoriaProdutoInput categoriaProdutoInput) {
		return modelMapper.map(categoriaProdutoInput, CategoriaProduto.class);
	}
	
	public void copyToDomainObject(CategoriaProdutoInput categoriaProdutoInput, CategoriaProduto categoriaProduto) {
		modelMapper.map(categoriaProdutoInput, categoriaProduto);
	}

}

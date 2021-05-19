package com.digitalSystems.extendsfood.api.disassempler;

import java.util.HashSet;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.digitalSystems.extendsfood.api.model.inputEntidade.ProdutoInput;
import com.digitalSystems.extendsfood.domain.model.CategoriaProduto;
import com.digitalSystems.extendsfood.domain.model.DiaSemana;
import com.digitalSystems.extendsfood.domain.model.Produto;

@Component
public class ProdutoInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Produto toDomainObject(ProdutoInput produtoInput) {
		return modelMapper.map(produtoInput, Produto.class);
	}
	
	public void copyToDomainObject(ProdutoInput produtoInput, Produto produto) {
		
		produto.setCategoriaProduto(new CategoriaProduto());
		produto.setDiasDisponiveis(new HashSet<DiaSemana>());
		modelMapper.map(produtoInput, produto);
	}
}

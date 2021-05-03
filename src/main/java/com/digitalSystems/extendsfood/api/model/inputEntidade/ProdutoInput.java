package com.digitalSystems.extendsfood.api.model.inputEntidade;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.digitalSystems.extendsfood.api.model.inputRelacionamento.CategoriaIdInput;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoInput {

	@NotBlank
	private String nome; 
	
	@NotBlank
	private String descricao;
	
	@NotNull
	private BigDecimal preco;
	
	@NotNull
	private Boolean disponivel;
	
	@Valid
	@NotNull
	private CategoriaIdInput categoriaProduto;
	
	@Valid
	@NotNull
	private List<ComplementoInput> complementos;
}

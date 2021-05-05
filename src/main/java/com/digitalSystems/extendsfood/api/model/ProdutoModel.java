package com.digitalSystems.extendsfood.api.model;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoModel {

	private Long id;
	
	private String nome; 
	
	private String descricao;
	
	private BigDecimal preco;
	
	private Boolean disponivel;
	
	private CategoriaProdutoModel categoriaProduto;
	
	private RestauranteResumoModel restaurante;
	
	private List<ComplementoModel> complementos;
	
	private List<DiaSemanaModel> diasDisponiveis;
}

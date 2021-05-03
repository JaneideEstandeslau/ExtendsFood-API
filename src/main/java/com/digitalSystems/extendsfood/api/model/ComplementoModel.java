package com.digitalSystems.extendsfood.api.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComplementoModel {

	private Long id;
	
	private String descricao;
	
	private Integer qtdMinima;
	
	private Integer qtdMaxima;
	
	private Boolean obrigatorio;
	
	private List<ItemComplementoModel> itens;
}

package com.digitalSystems.extendsfood.api.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaProdutoModel {

	private Long id;

	private String descricao;
	
	private List<PodutoResumoModel> produtos;
}

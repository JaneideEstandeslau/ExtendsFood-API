package com.digitalSystems.extendsfood.api.model;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaProdutoModel extends RepresentationModel<CategoriaProdutoModel>{

	@ApiModelProperty(example = "1", position = 5)
	private Long id;

	@ApiModelProperty(example = "Pizza tradicional", position = 10)
	private String descricao;
	
	@ApiModelProperty(position = 15)
	private RestauranteResumoModel restaurante;
	
	@ApiModelProperty(position = 20)
	private List<PodutoResumoModel> produtos;
	
}

package com.digitalSystems.extendsfood.api.model;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComplementoModel {

	@ApiModelProperty(example = "1", position = 5)
	private Long id;
	
	@ApiModelProperty(example = "Calabresa", position = 10)
	private String descricao;
	
	@ApiModelProperty(example = "1", position = 15)	
	private Integer qtdMinima;
	
	@ApiModelProperty(example = "5", position = 20)
	private Integer qtdMaxima;
	
	@ApiModelProperty(example = "true", position = 25)
	private Boolean obrigatorio;
	
	@ApiModelProperty(position = 30)
	private List<ItemComplementoModel> itens;
}

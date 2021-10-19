package com.digitalSystems.extendsfood.api.model;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PodutoResumoModel extends RepresentationModel<PodutoResumoModel>{

	@ApiModelProperty(example = "1", position = 5)
	private Long id;
	
	@ApiModelProperty(example = "Pizza de calabreza", position = 10)
	private String nome; 
	
	@ApiModelProperty(example = "Deliciosa ao molho de tomate caseiro", position = 15)
	private String descricao;
	
	@ApiModelProperty(example = "30.00", position = 20)
	private BigDecimal preco;
	
	@ApiModelProperty(example = "true", position = 25)
	private Boolean disponivel;
}

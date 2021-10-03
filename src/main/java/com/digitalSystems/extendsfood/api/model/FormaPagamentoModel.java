package com.digitalSystems.extendsfood.api.model;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoModel extends RepresentationModel<FormaPagamentoModel>{
	
	@ApiModelProperty(example = "1", position = 5)
	private Long id;
	
	@ApiModelProperty(example = "Cartão de Credito", position = 10)
	private String descricao;
}

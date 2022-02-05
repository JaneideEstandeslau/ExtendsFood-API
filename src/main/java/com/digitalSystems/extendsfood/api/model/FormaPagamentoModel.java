package com.digitalSystems.extendsfood.api.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "formasPagamento")
@Getter
@Setter
public class FormaPagamentoModel extends RepresentationModel<FormaPagamentoModel>{
	
	@ApiModelProperty(example = "1", position = 5)
	private Long id;
	
	@ApiModelProperty(example = "Cartão de Credito", position = 10)
	private String descricao;
}

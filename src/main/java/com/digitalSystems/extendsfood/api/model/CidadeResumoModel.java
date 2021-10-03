package com.digitalSystems.extendsfood.api.model;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CidadeResumoModel extends RepresentationModel<CidadeResumoModel>{

	@ApiModelProperty(example = "1", position = 5)
	private Long id;

	@ApiModelProperty(example = "Campina Grande", position = 10)
	private String nome;

	@ApiModelProperty(example = "Paraíba", position = 15)
	@JsonProperty("estado")
	private String estadoNome;

}
package com.digitalSystems.extendsfood.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CidadeResumoModel {

	private Long id;

	private String nome;

	@JsonProperty("estado")
	private String estadoNome;

}
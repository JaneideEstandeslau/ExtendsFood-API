package com.digitalSystems.extendsfood.domain.model.enums;

public enum DiasSemana {

	DOMINGO(1, "Domingo"),
	SEGUNDA(2, "Segunda-Feira"),
	TERCA(3, "Terça-Feira"),
	QUARTA(4, "Quarta-Feira"),
	QUINTA(5, "Quinta-Feira"),
	SEXTA(6, "Sexta-Feira"),
	SABADO(7, "Sabado");
	
	private Integer codigo;
	private String descricao;
	
	private DiasSemana(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}

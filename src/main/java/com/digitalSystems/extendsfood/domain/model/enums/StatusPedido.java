package com.digitalSystems.extendsfood.domain.model.enums;

public enum StatusPedido {

	AGUARDANDO_CONFIRMACAO(1, "Aguardando Confirmação"),
	CONFIRMADO(2, "Confirmado"),
	ENTREGUE(3, "Entregue"),
	CANCELADO(4, "Cancelado");
	
	private Integer codigo;
	private String descricao;
	
	private StatusPedido(Integer codigo, String descricao) {
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

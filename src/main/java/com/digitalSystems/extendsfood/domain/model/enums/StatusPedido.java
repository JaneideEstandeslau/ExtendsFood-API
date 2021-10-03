package com.digitalSystems.extendsfood.domain.model.enums;

import java.util.Arrays;
import java.util.List;

public enum StatusPedido {

	AGUARDANDO_CONFIRMACAO("Aguardando Confirmação"),
	CONFIRMADO("Confirmado", AGUARDANDO_CONFIRMACAO),
	SAUI_PARA_ENTREGA("Saiu para Entrega", CONFIRMADO),
	ENTREGUE("Entregue", SAUI_PARA_ENTREGA),
	CANCELADO("Cancelado", AGUARDANDO_CONFIRMACAO);
	
	private String descricao;
	private List<StatusPedido> statusAnteriores;
	
	private StatusPedido(String descricao, StatusPedido... statusAnteriores) {
		this.descricao = descricao;
		this.statusAnteriores = Arrays.asList(statusAnteriores);
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<StatusPedido> getStatusAnteriores() {
		return statusAnteriores;
	}

	public void setStatusAnteriores(List<StatusPedido> statusAnteriores) {
		this.statusAnteriores = statusAnteriores;
	}

	public boolean naoPodeAlterarPara(StatusPedido novoStatus) {
		return !novoStatus.statusAnteriores.contains(this);
	}
	
	public boolean podeAlterarPara(StatusPedido novoStatus) {
		return !naoPodeAlterarPara(novoStatus);
	}
}

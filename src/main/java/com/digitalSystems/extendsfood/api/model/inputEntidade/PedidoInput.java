package com.digitalSystems.extendsfood.api.model.inputEntidade;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.digitalSystems.extendsfood.api.model.inputRelacionamento.FormaPagamentoIdInput;
import com.digitalSystems.extendsfood.api.model.inputRelacionamento.RestauranteIdInput;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoInput {
	
	@NotBlank
	private String observacao;
	
	@NotNull
	private RestauranteIdInput restaurante;
	
	@NotNull
	private FormaPagamentoIdInput formaPagamento;
	
	@Valid
    @NotNull
	private List<ItemPedidoInput> itensPedido = new ArrayList<>();
}

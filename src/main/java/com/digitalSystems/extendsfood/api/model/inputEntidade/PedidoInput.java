package com.digitalSystems.extendsfood.api.model.inputEntidade;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.digitalSystems.extendsfood.api.model.inputRelacionamento.FormaPagamentoIdInput;
import com.digitalSystems.extendsfood.api.model.inputRelacionamento.RestauranteIdInput;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoInput {
	
	@NotBlank
	private String observacao;
	
	@Valid
	@NotNull
	private RestauranteIdInput restaurante;
	
	@Valid
	@NotNull
	private FormaPagamentoIdInput formaPagamento;
	
	@Valid
    @NotNull
    @Size(min = 1)
	private List<ItemPedidoInput> itensPedido = new ArrayList<>();
}

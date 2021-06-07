package com.digitalSystems.extendsfood.api.model.inputEntidade;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.digitalSystems.extendsfood.api.model.inputRelacionamento.FormaPagamentoIdInput;
import com.digitalSystems.extendsfood.api.model.inputRelacionamento.RestauranteIdInput;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoInput {
	
	@ApiModelProperty(example = "Sem cebola", required = true, position = 5)
	@NotBlank
	private String observacao;
	
	@ApiModelProperty(position = 10)
	@Valid
	@NotNull
	private RestauranteIdInput restaurante;
	
	@ApiModelProperty(position = 15)
	@Valid
	@NotNull
	private FormaPagamentoIdInput formaPagamento;
	
	@ApiModelProperty(position = 20)
	@Valid
    @NotNull
    @Size(min = 1)
	private List<ItemPedidoInput> itensPedido = new ArrayList<>();
}

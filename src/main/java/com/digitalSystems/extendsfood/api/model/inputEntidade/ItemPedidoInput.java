package com.digitalSystems.extendsfood.api.model.inputEntidade;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.digitalSystems.extendsfood.api.model.inputRelacionamento.ItemComplementoIdInput;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoInput {
	
	@NotNull
	private Long produtoId;
	
	@NotNull
	private Integer quantidade;	
	
	private List<ItemComplementoIdInput> itensComplemento = new ArrayList<>();
}

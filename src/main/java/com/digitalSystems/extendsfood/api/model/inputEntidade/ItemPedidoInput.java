package com.digitalSystems.extendsfood.api.model.inputEntidade;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.digitalSystems.extendsfood.api.model.inputRelacionamento.ItemComplementoIdInput;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoInput {
	
	@ApiModelProperty(example = "1", position = 5)
	@NotNull
	private Long produtoId;
	
	@ApiModelProperty(example = "10", position = 10)
	@NotNull
	private Integer quantidade;	
	
	@ApiModelProperty(position = 15)
	@Valid
	private List<ItemComplementoIdInput> itensComplemento = new ArrayList<>();
}

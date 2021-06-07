package com.digitalSystems.extendsfood.api.model.inputEntidade;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemComplementoInput {

	@ApiModelProperty(example = "Calabresa portuguesa", required = true, position = 5)
	@NotBlank
	private String nome;
	
	@ApiModelProperty(example = "5.00", position = 10)
	@NotNull
	private BigDecimal preco;
	
	@ApiModelProperty(example = "true", position = 15)
	@NotNull
	private Boolean disponivel;
}

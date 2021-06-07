package com.digitalSystems.extendsfood.api.model.inputEntidade;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComplementoInput {

	@ApiModelProperty(example = "Calabresa", required = true, position = 5)
	@NotBlank
	private String descricao;
	
	@ApiModelProperty(example = "1", position = 10)
	@NotNull
	private Integer qtdMinima;
	
	@ApiModelProperty(example = "5", position = 15)
	@NotNull
	private Integer qtdMaxima;
	
	@ApiModelProperty(example = "true", position = 20)
	@NotNull
	private Boolean obrigatorio;
	
	@ApiModelProperty(position = 25)
	@Valid
	@NotNull
	@Size(min = 1)
	private List<ItemComplementoInput> itens;
}

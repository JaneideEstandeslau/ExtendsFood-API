package com.digitalSystems.extendsfood.api.model.inputEntidade;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComplementoInput {

	@NotBlank
	private String descricao;
	
	@NotNull
	private Integer qtdMinima;
	
	@NotNull
	private Integer qtdMaxima;
	
	@NotNull
	private Boolean obrigatorio;
	
	@Valid
	@NotNull
	@Size(min = 1)
	private List<ItemComplementoInput> itens;
}

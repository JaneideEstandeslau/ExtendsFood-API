package com.digitalSystems.extendsfood.api.model.inputEntidade;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
	@NotEmpty
	private List<ItemComplementoInput> itens;
}

package com.digitalSystems.extendsfood.api.model.inputEntidade;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.digitalSystems.extendsfood.api.model.inputRelacionamento.DiaSemanaIdInput;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoInput {

	@ApiModelProperty(example = "Pizza de Calabresa", required = true, position = 5)
	@NotBlank
	private String nome; 
	
	@ApiModelProperty(example = "Deliciosa pizza de calabresa", required = true, position = 10)
	@NotBlank
	private String descricao;
	
	@ApiModelProperty(example = "30.00", position = 15)
	@NotNull
	private BigDecimal preco;
	
	@ApiModelProperty(example = "true", position = 20)
	@NotNull
	private Boolean disponivel;
	
	@ApiModelProperty(position = 25)
	@Valid
	@NotNull
	private List<ComplementoInput> complementos;
	
	@ApiModelProperty(position = 30)
	@Valid
	@Size(min = 1)
	@NotNull
	private List<DiaSemanaIdInput> diasDisponiveis;
}

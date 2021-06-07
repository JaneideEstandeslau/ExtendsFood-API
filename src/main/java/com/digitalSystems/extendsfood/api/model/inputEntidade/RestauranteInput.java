package com.digitalSystems.extendsfood.api.model.inputEntidade;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.br.CNPJ;

import com.digitalSystems.extendsfood.api.model.inputRelacionamento.CozinhaIdInput;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteInput {

	@ApiModelProperty(example = "Pizzaria do Paulosta", required = true, position = 5)
	@NotBlank
	private String nome;

	@ApiModelProperty(example = "18.216.124/0001-74", required = true, position = 10)
	@CNPJ
	@NotBlank
	private String cnpj;

	@ApiModelProperty(example = "(83) 99959-8974", required = true, position = 15)
	@NotBlank
	private String telefone;

	@ApiModelProperty(example = "10.00", position = 20)
	@NotNull
	@PositiveOrZero
	private BigDecimal taxaFrete;

	@ApiModelProperty(example = "17:00", position = 25)
	@NotNull
	private String horarioInicioFuncionamento;

	@ApiModelProperty(example = "23:00", position = 30)
	@NotNull
	private String horarioFimFuncionamento;
	
	@ApiModelProperty(position = 35)
	@Valid
	@NotNull
	private CozinhaIdInput cozinha;
	
	@ApiModelProperty(position = 40)
	@Valid
	@NotNull
	private EnderecoInput endereco;
}

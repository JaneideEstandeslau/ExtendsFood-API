package com.digitalSystems.extendsfood.api.model.inputEntidade;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.br.CNPJ;

import com.digitalSystems.extendsfood.api.model.inputRelacionamento.CozinhaIdInput;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteInput {

	@NotBlank
	private String nome;

	@CNPJ
	@NotBlank
	private String cnpj;

	@NotBlank
	private String telefone;

	@NotNull
	@PositiveOrZero
	private BigDecimal taxaFrete;

	@NotNull
	private String horarioInicioFuncionamento;

	@NotNull
	private String horarioFimFuncionamento;
	
	@Valid
	@NotNull
	private CozinhaIdInput cozinha;
	
	@Valid
	@NotNull
	private EnderecoInput endereco;
}

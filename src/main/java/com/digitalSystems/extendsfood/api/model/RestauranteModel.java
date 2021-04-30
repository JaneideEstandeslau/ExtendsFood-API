package com.digitalSystems.extendsfood.api.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteModel {

	private Long id;
	
	private String nome;

	private String cnpj;

	private String telefone;

	private BigDecimal taxaFrete;

	private Boolean aberto;
	
	private Boolean ativo;

	private String horarioInicioFuncionamento;

	private String horarioFimFuncionamento;
	
	private CozinhaModel cozinha;
	
	private EnderecoModel endereco;
}

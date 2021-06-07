package com.digitalSystems.extendsfood.api.model;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteModel {

	@ApiModelProperty(example = "1", position = 5)
	private Long id;
	
	@ApiModelProperty(example = "Pizzaria do Paulista", position = 5)
	private String nome;

	@ApiModelProperty(example = "18.216.124/0001-74", position = 10)
	private String cnpj;

	@ApiModelProperty(example = "(83) 995989-7874", position = 15)
	private String telefone;

	@ApiModelProperty(example = "10.00", position = 20)
	private BigDecimal taxaFrete;

	@ApiModelProperty(example = "true", position = 25)
	private Boolean aberto;
	
	@ApiModelProperty(example = "true", position = 30)
	private Boolean ativo;

	@ApiModelProperty(example = "17:00", position = 35)
	private String horarioInicioFuncionamento;

	@ApiModelProperty(example = "23:00", position = 40)
	private String horarioFimFuncionamento;
	
	@ApiModelProperty(position = 45)
	private CozinhaModel cozinha;
	
	@ApiModelProperty(position = 50)
	private EnderecoModel endereco;
}

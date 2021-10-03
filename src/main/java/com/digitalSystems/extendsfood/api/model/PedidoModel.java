package com.digitalSystems.extendsfood.api.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoModel extends RepresentationModel<PedidoModel>{

	@ApiModelProperty(example = "1", position = 5)
	private Long id;
	
	@ApiModelProperty(example = "394.50", position = 10)
	private BigDecimal subTotal;
	
	@ApiModelProperty(example = "10.00", position = 15)
	private BigDecimal taxaFrete;
	
	@ApiModelProperty(example = "404.50", position = 20)
	private BigDecimal valorTotal;
	
	@ApiModelProperty(example = "06/06/2021 15:29:19", position = 25)
	private LocalDateTime dataCriacao;
	
	@ApiModelProperty(example = "null", position = 30)
	private LocalDateTime dataConfirmacao;
	
	@ApiModelProperty(example = "null", position = 35)
	private LocalDateTime dataSaiuParaEntrega;
	
	@ApiModelProperty(example = "null", position = 40)
	private LocalDateTime dataEntrega;
	
	@ApiModelProperty(example = "null", position = 45)
	private LocalDateTime dataCancelamento;
	
	@ApiModelProperty(example = "AGUARDANDO_CONFIRMACAO", position = 50)
	private String status;
	
	@ApiModelProperty(example = "Sem cebola", position = 55)
	private String observacao;
	
	@ApiModelProperty(position = 60)
	private RestauranteResumoModel restaurante;
	
	@ApiModelProperty(position = 65)
	private FormaPagamentoModel formaPagamento;
	
	@ApiModelProperty(position = 70)
	private UsuarioModel cliente;
	
	@ApiModelProperty(position = 75)
	private EnderecoModel endereco;
	
	@ApiModelProperty(position = 80)
	private List<ItemPedidoModel> itensPedido;
}

package com.digitalSystems.extendsfood.api.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoModel {

	
	private Long id;
	
	private BigDecimal subTotal;
	
	private BigDecimal taxaFrete;
	
	private BigDecimal valorTotal;
	
	private LocalDateTime dataCriacao;
	
	private LocalDateTime dataConfirmacao;
	
	private LocalDateTime dataSaiuParaEntrega;
	
	private LocalDateTime dataEntrega;
	
	private LocalDateTime dataCancelamento;
	
	private String status;
	
	private String observacao;
	
	private RestauranteResumoModel restaurante;
	
	private FormaPagamentoModel formaPagamento;
	
	private UsuarioModel cliente;
	
	private EnderecoModel endereco;
	
	private List<ItemPedidoModel> itensPedido;
}

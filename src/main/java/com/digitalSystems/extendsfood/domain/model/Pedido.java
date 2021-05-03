package com.digitalSystems.extendsfood.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import com.digitalSystems.extendsfood.domain.model.enums.StatusPedido;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Pedido {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Column(nullable = false)
	private BigDecimal subTotal;
	
	@NotNull
	@Column(nullable = false)
	private BigDecimal taxaFrete;
	
	@NotNull
	@Column(nullable = false)
	private BigDecimal valorTotal;
	
	@NotNull
	@CreationTimestamp
	@Column(columnDefinition = "datetime")
	private LocalDateTime dataCriacao;
	
	@NotNull
	@Column(columnDefinition = "datetime")
	private LocalDateTime dataConfirmacao;
	
	@NotNull
	@Column(columnDefinition = "datetime")
	private LocalDateTime dataEntrega;
	
	@NotNull
	@Column(columnDefinition = "datetime")
	private LocalDateTime dataCancelamento;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private StatusPedido status = StatusPedido.AGUARDANDO_CONFIRMACAO;
	
	@NotBlank
	@Column(nullable = false)
	private String observacao;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "restaurante_id", nullable = false)
	private Restaurante restaurante;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "forma_pagamento_id", nullable = false)
	private FormaPagamento formaPagamento;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "usuario_cliente_id", nullable = false)
	private Usuario cliente;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "endereco_id", nullable = false)
	private Endereco endereco;
	
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<ItemPedido> itensPedido = new ArrayList<>();
	
	public void calcularValorTotal() {
	    getItensPedido().forEach(ItemPedido::calcularPrecoTotal);
	    
	    this.subTotal = getItensPedido().stream()
	        .map(item -> item.getPrecoTotal())
	        .reduce(BigDecimal.ZERO, BigDecimal::add);
	    
	    this.valorTotal = this.subTotal.add(this.taxaFrete);
	}
}

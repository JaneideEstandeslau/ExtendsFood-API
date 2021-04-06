package com.digitalSystems.extendsfood.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import com.digitalSystems.extendsfood.domain.model.enums.StatusPedido;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime dataCriacao;
	
	@NotNull
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime dataConfirmacao;
	
	@NotNull
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime dataEntrega;
	
	@NotNull
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime dataCancelamento;
	
	@Enumerated(EnumType.STRING)
	private StatusPedido status;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "restaurante_id", nullable = false)
	private Restaurante restaurante;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "forma_pagamento_id", nullable = false)
	private FormaPagamento formaPagamento;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "usuario_cliente_id", nullable = false)
	private Usuario cliente;
	
	@OneToMany(mappedBy = "pedido")
	private List<ItemPedido> itensPedido = new ArrayList<>();	
}

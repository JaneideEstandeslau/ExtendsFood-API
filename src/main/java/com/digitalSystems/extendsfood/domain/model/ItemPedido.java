package com.digitalSystems.extendsfood.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class ItemPedido {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(nullable = false)
	private BigDecimal precoUnitario;
	
	@NotNull
	@Column(nullable = false)
	private BigDecimal precoTotal;
	
	@NotNull
	@Column(nullable = false)
	private Integer quantidade;
	
	@NotBlank
	@Column(nullable = false)
	private String observacao;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "pedido_id", nullable = false)
	private Pedido pedido;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name= "produto_id", nullable = false)
	private Produto produto;
}

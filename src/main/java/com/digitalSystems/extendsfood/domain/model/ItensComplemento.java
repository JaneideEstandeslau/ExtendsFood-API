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
public class ItensComplemento {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long d;
	
	@NotBlank
	@Column(nullable = false)
	private String nome;
	
	@NotBlank
	@Column(nullable = false)
	private String descricao;
	
	@NotNull
	@Column(nullable = false)
	private BigDecimal preco;
	
	@NotNull
	@Column(nullable = false)
	private Boolean disponivel;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "complemento_id", nullable = false)
	private Complemento complemento;
}

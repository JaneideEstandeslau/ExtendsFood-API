package com.digitalSystems.extendsfood.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.br.CNPJ;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true) 
@Entity
public class Restaurante {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(nullable = false)
	private String nome;
	
	@CNPJ
	@NotBlank
	@Column(nullable = false)
	private String cnpj;
	
	
	@NotBlank
	@Column(nullable = false)
	private String telefone;
	
	@PositiveOrZero
	@NotNull
	@Column(name = "taxa_frete", nullable = false)
	private BigDecimal taxaFrete;
	
	private Boolean ativo = Boolean.TRUE;
	
	private Boolean aberto  = Boolean.FALSE;
	
	@Column(nullable = false)
	private String horarioInicioFuncionamento;
	
	@Column(nullable = false)
	private String horarioFimFuncionamento;
	
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime dataAtualizacao;
	
	@ManyToOne
	@JoinColumn(name = "cozinha_id", nullable = false)
	private Cozinha cozinha;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "restaurante_forma_pagamento",
			joinColumns = @JoinColumn(name = "restaurante_id"),
			inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
	private Set<FormaPagamento> formasPagamento = new HashSet<>();
	
	@JsonIgnore
	@OneToOne
	private Endereco endereco;
	
	@JsonIgnore
	@OneToMany(mappedBy = "restaurante")
	private List<Produto> cardapio = new ArrayList<>();	

}

package com.digitalSystems.extendsfood.domain.model;

import java.math.BigDecimal;
import java.util.HashSet;
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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Produto {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
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
	
	@ManyToOne
	@JoinColumn(name = "restaurante_id", nullable = false)
	private Restaurante restaurante;
	
	@ManyToOne
	@JoinColumn(name = "categoria_produto_id", nullable = false)
	private CategoriaProduto categoriaProduco;
	
	@ManyToMany
	@JoinTable(name = "produto_dias_disponiveis",
			joinColumns = @JoinColumn(name = "produto_id"),
			inverseJoinColumns = @JoinColumn(name = "dia_disponivel_id"))
	private Set<DiasDisponiveis> diasDisponiveis = new HashSet<>();
}

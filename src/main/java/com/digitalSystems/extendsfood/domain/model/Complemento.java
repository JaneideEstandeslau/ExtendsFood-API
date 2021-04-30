package com.digitalSystems.extendsfood.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Complemento {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(nullable = false)
	private String descricao;
	
	@NotNull
	@Column(nullable = false)
	private Integer qtdMinima;
	
	@NotNull
	@Column(nullable = false)
	private Integer qtdMaxima;
	
	@NotNull
	@Column(nullable = false)
	private Boolean obrigatorio;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "produto_id", nullable = false)
	private Produto produto;
	
	@OneToMany(mappedBy = "complemento", cascade = CascadeType.ALL)
	private List<ItensComplemento> itens = new ArrayList<>();
}

package com.digitalSystems.extendsfood.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.digitalSystems.extendsfood.core.config.Groups;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Endereco {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(nullable = false)
	private String cep;

	@NotBlank
	@Column(nullable = false)
	private String rua;

	@NotNull
	@Column(nullable = false)
	private String numero;

	@Column(nullable = false)
	private String complemento;

	@NotBlank
	@Column(nullable = false)
	private String bairro;


	@Valid
	@ConvertGroup(from = Default.class, to = Groups.CidadeId.class)
	@NotNull
	@ManyToOne
	@JoinColumn(name = "endereco_cidade_id")
	private Cidade cidade;

}

package com.digitalSystems.extendsfood.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Endereco {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String cep;

	@Column(nullable = false)
	private String rua;

	@Column(nullable = false)
	private String numero;

	@Column(nullable = false)
	private String complemento;

	@Column(nullable = false)
	private String bairro;
	
	@Column(name = "ativo_usuario")
	private Boolean enderecoAtivoUsuario;

	@ManyToOne
	@JoinColumn(name = "endereco_usuario_id")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "endereco_cidade_id")
	private Cidade cidade;
	
	public void enderecoInativo() {
		setEnderecoAtivoUsuario(false);
	}

}

package com.digitalSystems.extendsfood.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Usuario {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String cpf;

	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String senha;
	
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime dataCadastro;
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private Set<Endereco> enderecos = new HashSet<>();
	
	@ManyToMany
	@JoinTable(name = "usuario_grupo", joinColumns = @JoinColumn(name = "usuario_id"),
			inverseJoinColumns = @JoinColumn(name = "grupo_id"))
	private Set<Grupo> grupos = new HashSet<>();
	
	public void desativerEnderecosExistendes() {
		getEnderecos().forEach(Endereco::enderecoInativo);
	}
	
	public Boolean senhasCoincidem(String senhaAtual) {
		return getSenha().equals(senhaAtual);
	}
	
	public boolean removerGrupo(Grupo grupo) {
	    return getGrupos().remove(grupo);
	}

	public boolean adicionarGrupo(Grupo grupo) {
	    return getGrupos().add(grupo);
	}
	
	public boolean adicionarEndereco(Endereco endereco) {
		return getEnderecos().add(endereco);
	}
	
	public boolean removerEndereco(Endereco endereco) {
		return getEnderecos().remove(endereco);
	}
	
}
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
	
	@Column(nullable = false)
	private BigDecimal precoUnitario;
	
	@Column(nullable = false)
	private BigDecimal precoTotal;
	
	@Column(nullable = false)
	private Integer quantidade;
	
	@ManyToOne
	@JoinColumn(name = "pedido_id", nullable = false)
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(name= "produto_id", nullable = false)
	private Produto produto;
	
	@ManyToMany
	@JoinTable(name = "item_pedido_item_complemento",
			joinColumns = @JoinColumn(name = "item_pedido_id"),
			inverseJoinColumns = @JoinColumn(name = "item_complemento_id"))
	private Set<ItemComplemento> itensComplemento = new HashSet<>();
	
	public void calcularPrecoTotal() {
		BigDecimal precoUnitario = this.getPrecoUnitario();
	    Integer quantidade = this.getQuantidade();

	    if (precoUnitario == null) {
	        precoUnitario = BigDecimal.ZERO;
	    }

	    if (quantidade == null) {
	        quantidade = 0;
	    }

	    this.setPrecoTotal(precoUnitario.multiply(new BigDecimal(quantidade)));
	}
}

package com.digitalSystems.extendsfood.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.domain.AbstractAggregateRoot;

import com.digitalSystems.extendsfood.domain.event.PedidoCanceladoEvent;
import com.digitalSystems.extendsfood.domain.event.PedidoConfirmadoEvent;
import com.digitalSystems.extendsfood.domain.exception.NegocioException;
import com.digitalSystems.extendsfood.domain.model.enums.StatusPedido;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
public class Pedido extends AbstractAggregateRoot<Pedido>{

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private BigDecimal subTotal;
	
	@Column(nullable = false)
	private BigDecimal taxaFrete;
	
	@Column(nullable = false)
	private BigDecimal valorTotal;
	
	@CreationTimestamp
	@Column(columnDefinition = "datetime")
	private LocalDateTime dataCriacao;
	
	@Column(columnDefinition = "datetime")
	private LocalDateTime dataConfirmacao;
	
	@Column(columnDefinition = "datetime")
	private LocalDateTime dataSaiuParaEntrega;
	
	@Column(columnDefinition = "datetime")
	private LocalDateTime dataEntrega;
	
	@Column(columnDefinition = "datetime")
	private LocalDateTime dataCancelamento;
	
	@Enumerated(EnumType.STRING)
	private StatusPedido status = StatusPedido.AGUARDANDO_CONFIRMACAO;
	
	@Column(nullable = false)
	private String observacao;
	
	@ManyToOne
	@JoinColumn(name = "restaurante_id", nullable = false)
	private Restaurante restaurante;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "forma_pagamento_id", nullable = false)
	private FormaPagamento formaPagamento;
	
	@ManyToOne
	@JoinColumn(name = "usuario_cliente_id", nullable = false)
	private Usuario cliente;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "endereco_id", nullable = false)
	private Endereco endereco;
	
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<ItemPedido> itensPedido = new ArrayList<>();
	
	public void calcularValorTotal() {
	    getItensPedido().forEach(ItemPedido::calcularPrecoTotal);
	    
	    this.subTotal = getItensPedido().stream()
	        .map(item -> item.getPrecoTotal())
	        .reduce(BigDecimal.ZERO, BigDecimal::add);
	    
	    this.valorTotal = this.subTotal.add(this.taxaFrete);
	}
	
	public void confirmar() {
		setStatus(StatusPedido.CONFIRMADO);
		setDataConfirmacao(LocalDateTime.now());
		
		registerEvent(new PedidoConfirmadoEvent(this));
	}
	
	public void SaiuParaEntrega() {
		setStatus(StatusPedido.SAUI_PARA_ENTREGA);
		setDataSaiuParaEntrega(LocalDateTime.now());
	}
	
	public void entregar() {
		setStatus(StatusPedido.ENTREGUE);
		setDataEntrega(LocalDateTime.now());
	}
	
	public void cancelar() {
		setStatus(StatusPedido.CANCELADO);
		setDataCancelamento(LocalDateTime.now());
		
		registerEvent(new PedidoCanceladoEvent(this));
	}
	
	private void setStatus(StatusPedido novoStatus) {
		if(getStatus().naoPodeAlterarPara(novoStatus)) {
			throw new NegocioException(
					String.format("Status do pedido %d não pode ser alterado de %s para %s",
							getId(), getStatus().getDescricao(), 
							novoStatus.getDescricao()));
		}
		
		this.status = novoStatus;
	}
}

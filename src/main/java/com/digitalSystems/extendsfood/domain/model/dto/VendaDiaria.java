package com.digitalSystems.extendsfood.domain.model.dto;
import java.math.BigDecimal;
import java.util.Date;

import com.digitalSystems.extendsfood.domain.model.enums.StatusPedido;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class VendaDiaria {

	private Date data;
	private String retauranteNome;
	private StatusPedido status;
	private String formaPagamento;
	private Long totalVendas;
	private BigDecimal totalFaturado;
	
}
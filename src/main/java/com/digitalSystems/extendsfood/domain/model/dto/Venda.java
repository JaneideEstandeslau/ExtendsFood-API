package com.digitalSystems.extendsfood.domain.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class Venda {

	private LocalDateTime dataInicio;
	private LocalDateTime dataFim;
	private Integer quantidadeVendas;
	private BigDecimal totalFaturado;
	private List<VendaDiaria> vendas = new ArrayList<>();
	
	public void calcularTotalVendas() {
		 this.totalFaturado = getVendas().stream()
			        .map(venda -> venda.getTotalFaturado())
			        .reduce(BigDecimal.ZERO, BigDecimal::add);
		 
		 this.quantidadeVendas = this.vendas.size();
	}
}

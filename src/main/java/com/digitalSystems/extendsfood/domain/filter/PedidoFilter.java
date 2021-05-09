package com.digitalSystems.extendsfood.domain.filter;


import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.digitalSystems.extendsfood.domain.model.enums.StatusPedido;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PedidoFilter {

	private Long produtoId;
	
	private Long restauranteId;
	
	private StatusPedido status;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime dataCriacaoInicio;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime dataCriacaoFim;
	
}
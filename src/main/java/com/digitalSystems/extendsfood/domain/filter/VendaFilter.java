package com.digitalSystems.extendsfood.domain.filter;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VendaFilter {

	private Long restauranteId;
	
	@NotNull
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime dataCriacaoInicio;
	
	@NotNull
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime dataCriacaoFim;

}

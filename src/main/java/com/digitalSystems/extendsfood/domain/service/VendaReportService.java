package com.digitalSystems.extendsfood.domain.service;

import com.digitalSystems.extendsfood.domain.filter.VendaFilter;

public interface VendaReportService {

	byte[] emitirVendasDiarias(VendaFilter filtro);
	
}
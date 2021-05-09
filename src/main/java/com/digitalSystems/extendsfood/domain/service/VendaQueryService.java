package com.digitalSystems.extendsfood.domain.service;
import java.util.List;

import com.digitalSystems.extendsfood.domain.filter.VendaFilter;
import com.digitalSystems.extendsfood.domain.model.dto.VendaDiaria;

public interface VendaQueryService {

	List<VendaDiaria> consultarVendas(VendaFilter filtro);
	
}
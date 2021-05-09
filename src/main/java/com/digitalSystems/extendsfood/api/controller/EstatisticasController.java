package com.digitalSystems.extendsfood.api.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitalSystems.extendsfood.domain.filter.VendaFilter;
import com.digitalSystems.extendsfood.domain.model.dto.VendaDiaria;
import com.digitalSystems.extendsfood.domain.service.VendaQueryService;

@RestController
@RequestMapping(path = "/estatisticas")
public class EstatisticasController {

	@Autowired
	private VendaQueryService vendaQueryService;	

	@GetMapping(path = "/vendas-diarias")
	public List<VendaDiaria> consultarVendasDiarias(VendaFilter filtro) {
		
		return vendaQueryService.consultarVendas(filtro);
	}
	
	
	
}
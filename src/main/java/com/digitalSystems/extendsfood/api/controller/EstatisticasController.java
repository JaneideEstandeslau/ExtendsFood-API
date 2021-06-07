package com.digitalSystems.extendsfood.api.controller;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitalSystems.extendsfood.api.openapi.controller.EstatisticasControllerOpenApi;
import com.digitalSystems.extendsfood.domain.filter.VendaFilter;
import com.digitalSystems.extendsfood.domain.model.dto.Venda;
import com.digitalSystems.extendsfood.domain.model.dto.VendaDiaria;
import com.digitalSystems.extendsfood.domain.service.VendaQueryService;
import com.digitalSystems.extendsfood.domain.service.VendaReportService;

@RestController
@RequestMapping(path = "/estatisticas")
public class EstatisticasController implements EstatisticasControllerOpenApi{

	@Autowired
	private VendaQueryService vendaQueryService;
	
	@Autowired
	private VendaReportService vendaReportService;

	@GetMapping(path = "/vendas", produces = MediaType.APPLICATION_JSON_VALUE)
	public Venda consultarVendas(@Valid VendaFilter filtro) {
		
		List<VendaDiaria> vendasDiadias = vendaQueryService.consultarVendas(filtro);
		
		Venda venda = new Venda(filtro.getDataCriacaoInicio(), filtro.getDataCriacaoFim(), null, null, vendasDiadias);
		venda.calcularTotalVendas();
		
		return venda;
	}
	
	@GetMapping(path = "/vendas", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> consultarVendasPdf(@Valid VendaFilter filtro) {

		byte[] bytesPdf = vendaReportService.emitirVendasDiarias(filtro);
		
		var headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=relatorio-vendas.pdf");
		
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_PDF)
				.headers(headers)
				.body(bytesPdf);
	}
	
	
	
}
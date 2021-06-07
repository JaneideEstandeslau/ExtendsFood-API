package com.digitalSystems.extendsfood.api.openapi.controller;

import org.springframework.http.ResponseEntity;

import com.digitalSystems.extendsfood.domain.filter.VendaFilter;
import com.digitalSystems.extendsfood.domain.model.dto.Venda;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Estatísticas")
public interface EstatisticasControllerOpenApi {

	@ApiOperation("Consulta estatísticas de vendas diárias")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "restauranteId", value = "ID do restaurante", 
				example = "1", dataType = "int"),
		@ApiImplicitParam(name = "dataCriacaoInicio", value = "Data/hora inicial da criação do pedido",
			example = "2021-06-01T15:00", dataType = "date-time"),
		@ApiImplicitParam(name = "dataCriacaoFim", value = "Data/hora final da criação do pedido",
			example = "2021-06-30T23:00", dataType = "date-time")
	})
	Venda consultarVendas(VendaFilter filtro);

	ResponseEntity<byte[]> consultarVendasPdf(VendaFilter filtro);

}
package com.digitalSystems.extendsfood.api.openapi.controller;

import java.util.List;

import com.digitalSystems.extendsfood.api.model.DiaSemanaModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Dias da Semana")
public interface DiaSemanaControllerOpenApi {

	@ApiOperation("Lista todos os Dias da Semana")
	public List<DiaSemanaModel> listar();
}

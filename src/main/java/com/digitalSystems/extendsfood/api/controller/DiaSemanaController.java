package com.digitalSystems.extendsfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitalSystems.extendsfood.api.assembler.DiaSemanaModelAssembler;
import com.digitalSystems.extendsfood.api.model.DiaSemanaModel;
import com.digitalSystems.extendsfood.api.openapi.controller.DiaSemanaControllerOpenApi;
import com.digitalSystems.extendsfood.core.security.CheckSecurity;
import com.digitalSystems.extendsfood.domain.model.DiaSemana;
import com.digitalSystems.extendsfood.domain.repository.ProdutoRepository;

@RestController
@RequestMapping(path = "/dias-semana", produces = MediaType.APPLICATION_JSON_VALUE)
public class DiaSemanaController implements DiaSemanaControllerOpenApi{

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private DiaSemanaModelAssembler diaSemanaAssembler;
	
	@CheckSecurity.Restaurantes.PodeConsultar
	@GetMapping
	public List<DiaSemanaModel> listar(){		
		List<DiaSemana> diasSemana = produtoRepository.findByDiaSemana();
		
		return diaSemanaAssembler.toCollectionModel(diasSemana);
	}
}

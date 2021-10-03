package com.digitalSystems.extendsfood.api.openapi.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.digitalSystems.extendsfood.api.exceptionHandler.Problem;
import com.digitalSystems.extendsfood.api.model.PedidoModel;
import com.digitalSystems.extendsfood.api.model.PedidoResumoModel;
import com.digitalSystems.extendsfood.api.model.inputEntidade.PedidoInput;
import com.digitalSystems.extendsfood.domain.filter.PedidoFilter;
import com.digitalSystems.extendsfood.domain.model.dto.PedidoUsuario;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Pedidos")
public interface PedidoControllerOpenApi {

	@ApiOperation("Pesquisa os pedidos")
	PagedModel<PedidoResumoModel> pesuisar(PedidoFilter filtro, Pageable pageable);
	
	@ApiOperation("Busca os Pedidos de um Cliente")
	List<PedidoUsuario> buscarPedidosUsuario();
    
	@ApiOperation("Busca um Pedido por código")
	@ApiResponses({
		@ApiResponse(code = 404, message = "Pedido não encontrado", response = Problem.class),
		@ApiResponse(code = 400, message = "ID do Pedido inválido", response = Problem.class)
	})
	PedidoModel buscar(Long pedidoId);   
    
	@ApiOperation("Registra um Pedido")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Pedido registrado"),
	})
    PedidoModel adicionar(
    		@ApiParam(name = "corpo", value = "Representação de um novo pedido", required = true)
    		PedidoInput pedidoInput);
}

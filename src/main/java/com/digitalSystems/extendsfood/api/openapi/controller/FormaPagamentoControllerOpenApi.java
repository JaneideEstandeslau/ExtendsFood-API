package com.digitalSystems.extendsfood.api.openapi.controller;

import java.util.List;

import com.digitalSystems.extendsfood.api.exceptionHandler.Problem;
import com.digitalSystems.extendsfood.api.model.FormaPagamentoModel;
import com.digitalSystems.extendsfood.api.model.inputEntidade.FormaPagamentoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Formas de Pagamento")
public interface FormaPagamentoControllerOpenApi {

	@ApiOperation("Lista todas as Formas de Pagamento")
	List<FormaPagamentoModel> listar();

	@ApiOperation("Busca uma Forma de Pagamento por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID da Forma de Pagamento inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Forma de Pagamento não encontrado", response = Problem.class)
	})
	FormaPagamentoModel buscar(
			@ApiParam(value = "ID da Forma de Pagameno", example = "1", required = true)
			Long formaPagamentoId);

	@ApiOperation("Cadastra uma Forma de Pagamento")
	@ApiResponses({ 
		@ApiResponse(code = 201, message = "Forma de Pagamento cadastrada")
	})
	FormaPagamentoModel adicionar(
			@ApiParam(name = "corpo", value = "Representação de uma nova Forma de Pagamento", required = true)
			FormaPagamentoInput formaPagamentoInput);
	
	@ApiOperation("Atualiza uma Forma de Pagamento por ID")
	@ApiResponses({ 
		@ApiResponse(code = 404, message = "Forma de Pagamento não encontrada", response = Problem.class),
		@ApiResponse(code = 200, message = "Forma de Pagamento atualizada")
	})
	FormaPagamentoModel atualizar(
			@ApiParam(value = "ID da Forma de Pagamento", example = "1", required = true)
			Long formaPagamentoId, 
			
			@ApiParam(name = "corpo", value = "Representação de uma Forma de Pagamento com novos dados", required = true)
			FormaPagamentoInput formaPagamentoInput);

	@ApiOperation("Exclui uma Forma de Pagamento por ID")
	@ApiResponses({ 
		@ApiResponse(code = 204, message = "Forma de Pagamento excluída"),			
		@ApiResponse(code = 404, message = "Forma de Pagamento não encontrada", response = Problem.class) 
	})
	void remover(
			@ApiParam(value = "ID da Forma de Pagamento", example = "1", required = true)
			Long formaPagamentoId);
}

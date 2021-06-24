package com.digitalSystems.extendsfood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.digitalSystems.extendsfood.api.exceptionHandler.Problem;
import com.digitalSystems.extendsfood.api.model.UsuarioEnderecoModel;
import com.digitalSystems.extendsfood.api.model.UsuarioModel;
import com.digitalSystems.extendsfood.api.model.inputEntidade.EnderecoUsuarioInput;
import com.digitalSystems.extendsfood.api.model.inputEntidade.SenhaInput;
import com.digitalSystems.extendsfood.api.model.inputEntidade.UsuarioComSenhaInput;
import com.digitalSystems.extendsfood.api.model.inputEntidade.UsuarioInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Usuários")
public interface UsuarioControllerOpenApi {

	@ApiOperation("Lista os Usuários")
	CollectionModel<UsuarioModel> listar();

	@ApiOperation("Busca um Usuário por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do Usuário inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
	})
	UsuarioModel buscar(
			@ApiParam(value = "ID do Usuário", example = "1", required = true)
			Long usuarioId);

	@ApiOperation("Cadastra um Usuário")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Usuário cadastrado"),
	})
	UsuarioModel salvar(
			@ApiParam(name = "corpo", value = "Representação de um novo Usuário", required = true)
			UsuarioComSenhaInput usuarioInput);
	
	@ApiOperation("Atualiza um Usuário por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Usuário atualizado"),
		@ApiResponse(code = 400, message = "ID inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
	})
	UsuarioModel atualizar(
			@ApiParam(name = "corpo", value = "Representação de um Usuário com os novos dados", required = true)
			UsuarioInput usuarioInput,
			
			@ApiParam(value = "ID do Usuário", example = "1", required = true)
			Long usuarioId);
			
	
	@ApiOperation("Atualiza o Endereço do Usuário")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Endereço do Usuário atualizado"),
		@ApiResponse(code = 400, message = "ID inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
	})
	UsuarioEnderecoModel atualizarEndereco(
			@ApiParam(name = "corpo", value = "Representação de uma novo Endereço", required = true)			
			EnderecoUsuarioInput enderecoUsuarioInput, 
			
			@ApiParam(value = "ID do Usuário", example = "1", required = true)
			Long usuarioId);

	@ApiOperation("Atualiza a senha de um Usuário")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Senha alterada com sucesso"),
		@ApiResponse(code = 400, message = "ID inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
	})
	
	void atualizarSenha(
			@ApiParam(name = "corpo", value = "Representação de uma nova senha", required = true)
			SenhaInput senha,
			
			@ApiParam(value = "ID do Usuário", example = "1", required = true)
			Long usuarioId);
			
}

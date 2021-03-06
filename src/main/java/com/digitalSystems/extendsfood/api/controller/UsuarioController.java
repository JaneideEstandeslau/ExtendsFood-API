package com.digitalSystems.extendsfood.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.digitalSystems.extendsfood.api.assembler.UsuarioEnderecoModelAssembler;
import com.digitalSystems.extendsfood.api.assembler.UsuarioModelAssembler;
import com.digitalSystems.extendsfood.api.disassempler.EnderecoUsuarioInputDisassembler;
import com.digitalSystems.extendsfood.api.disassempler.UsuarioInputDisassembler;
import com.digitalSystems.extendsfood.api.model.UsuarioEnderecoModel;
import com.digitalSystems.extendsfood.api.model.UsuarioModel;
import com.digitalSystems.extendsfood.api.model.inputEntidade.EnderecoUsuarioInput;
import com.digitalSystems.extendsfood.api.model.inputEntidade.SenhaInput;
import com.digitalSystems.extendsfood.api.model.inputEntidade.UsuarioComSenhaInput;
import com.digitalSystems.extendsfood.api.model.inputEntidade.UsuarioInput;
import com.digitalSystems.extendsfood.api.openapi.controller.UsuarioControllerOpenApi;
import com.digitalSystems.extendsfood.domain.model.Endereco;
import com.digitalSystems.extendsfood.domain.model.Usuario;
import com.digitalSystems.extendsfood.domain.repository.UsuarioRepository;
import com.digitalSystems.extendsfood.domain.service.UsuarioService;

@RestController
@RequestMapping(path = "/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController implements UsuarioControllerOpenApi{

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioModelAssembler usuarioAssembler;
	
	@Autowired
	private UsuarioInputDisassembler usuarioDisassembler;
	
	@Autowired
	private EnderecoUsuarioInputDisassembler enderecoUsuarioDisassembler;
	
	@Autowired
	private UsuarioEnderecoModelAssembler usuarioEnderecoDisassembler;

	@GetMapping
	public CollectionModel<UsuarioModel> listar() {
		return usuarioAssembler.toCollectionModel(usuarioRepository.findAll());
	}

	@GetMapping("/{usuarioId}")
	public UsuarioModel buscar(@PathVariable Long usuarioId) {
		Usuario usuario = usuarioService.buscarOuFalhar(usuarioId);
		
		return usuarioAssembler.toModel(usuario);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioModel salvar(@RequestBody @Valid UsuarioComSenhaInput usuarioInput) {
		Usuario usuario = usuarioDisassembler.toDomainObject(usuarioInput);
		
		return usuarioAssembler.toModel(usuarioService.salvar(usuario));
	}
	
	@PutMapping("/{usuarioId}")
	public UsuarioModel atualizar(@RequestBody @Valid UsuarioInput usuarioInput, @PathVariable Long usuarioId) {
		
		Usuario usuarioAtual = usuarioService.buscarOuFalhar(usuarioId);
		
		usuarioDisassembler.copyToDomainObject(usuarioInput, usuarioAtual);
		
		return usuarioAssembler.toModel(usuarioService.atuaizar(usuarioAtual));
	}
	
	@PutMapping("/{usuarioId}/endereco")
	public UsuarioEnderecoModel atualizarEndereco(@RequestBody @Valid EnderecoUsuarioInput enderecoUsuarioInput, 
			@PathVariable Long usuarioId) {
		
		Endereco endereco = enderecoUsuarioDisassembler.toDomainObject(enderecoUsuarioInput);
		
		Usuario usuario =  usuarioService.adicionarEndereco(endereco, usuarioId);
		
		return usuarioEnderecoDisassembler.toModel(usuario);
	}
	
	@PutMapping("/{usuarioId}/senha")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarSenha(@RequestBody @Valid SenhaInput senhaInput, @PathVariable Long usuarioId) {
		usuarioService.atualizarSenha(usuarioId, senhaInput.getSenhaAtual(), senhaInput.getNovaSenha());
	}
}

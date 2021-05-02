package com.digitalSystems.extendsfood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.digitalSystems.extendsfood.api.assembler.UsuarioModelAssembler;
import com.digitalSystems.extendsfood.api.disassempler.UsuarioInputDisassembler;
import com.digitalSystems.extendsfood.api.model.UsuarioModel;
import com.digitalSystems.extendsfood.api.model.inputEntidade.SenhaInput;
import com.digitalSystems.extendsfood.api.model.inputEntidade.UsuarioComSenhaInput;
import com.digitalSystems.extendsfood.api.model.inputEntidade.UsuarioInput;
import com.digitalSystems.extendsfood.domain.model.Usuario;
import com.digitalSystems.extendsfood.domain.repository.UsuarioRepository;
import com.digitalSystems.extendsfood.domain.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioModelAssembler usuarioAssembler;
	
	@Autowired
	private UsuarioInputDisassembler usuarioDisassembler;

	@GetMapping
	public List<UsuarioModel> listar() {
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
		
		return usuarioAssembler.toModel(usuarioService.salvar(usuarioAtual));
	}
	
	@PutMapping("/{usuarioId}/senha")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarSenha(@RequestBody @Valid SenhaInput senhaInput, @PathVariable Long usuarioId) {
		usuarioService.atualizarSenha(usuarioId, senhaInput.getSenhaAtual(), senhaInput.getNovaSenha());
	}
}

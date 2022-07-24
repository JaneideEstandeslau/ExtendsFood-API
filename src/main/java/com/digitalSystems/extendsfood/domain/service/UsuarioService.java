package com.digitalSystems.extendsfood.domain.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.digitalSystems.extendsfood.domain.event.ConfirmarCadastradoEvent;
import com.digitalSystems.extendsfood.domain.exception.CpfJaCadastradoException;
import com.digitalSystems.extendsfood.domain.exception.EmailJaCadastradoException;
import com.digitalSystems.extendsfood.domain.exception.NegocioException;
import com.digitalSystems.extendsfood.domain.exception.UsuarioNaoEncontradoException;
import com.digitalSystems.extendsfood.domain.model.Cidade;
import com.digitalSystems.extendsfood.domain.model.Endereco;
import com.digitalSystems.extendsfood.domain.model.Grupo;
import com.digitalSystems.extendsfood.domain.model.Usuario;
import com.digitalSystems.extendsfood.domain.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private GrupoService grupoService;

	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;  

	@Transactional
	public Usuario salvar(Usuario usuario) {

		usuarioRepository.detach(usuario);

		validarUsuario(usuario);
		
		if (usuario.isNovo()) {
			usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		}

		usuario = usuarioRepository.save(usuario);	

		ConfirmarCadastradoEvent usuarioCadastradoEvent = new ConfirmarCadastradoEvent(this, usuario);
		applicationEventPublisher.publishEvent(usuarioCadastradoEvent);

		return usuario;
	}

	@Transactional
	public Usuario atuaizar(Usuario usuario) {
		usuarioRepository.detach(usuario);

		validarUsuario(usuario);

		return usuarioRepository.save(usuario);
	}

	@Transactional
	public void atualizarSenha(Long usuarioId, String senhaAtual, String novaSenha) {

		Usuario usuario = buscarOuFalhar(usuarioId);

		if (!passwordEncoder.matches(senhaAtual, usuario.getSenha())) {
	        throw new NegocioException("Senha atual informada não coincide com a senha do usuário.");
	    }
	    
	    usuario.setSenha(passwordEncoder.encode(novaSenha));
	}

	@Transactional
	public Usuario adicionarEndereco(Endereco endereco, Long usuarioId) {

		Long cidadeId = endereco.getCidade().getId();

		Cidade cidade = cidadeService.buscarOuFalhar(cidadeId);
		Usuario usuario = buscarOuFalhar(usuarioId);

		endereco.setUsuario(usuario);
		endereco.setCidade(cidade);
		endereco.setEnderecoAtivoUsuario(true);

		usuario.desativerEnderecosExistendes();
		usuario.adicionarEndereco(endereco);

		return usuario;
	}

	@Transactional
	public void desassociarGrupo(Long usuarioId, Long grupoId) {
		Usuario usuario = buscarOuFalhar(usuarioId);
		Grupo grupo = grupoService.buscarOuFalhar(grupoId);

		usuario.removerGrupo(grupo);
	}

	@Transactional
	public void associarGrupo(Long usuarioId, Long grupoId) {
		Usuario usuario = buscarOuFalhar(usuarioId);
		Grupo grupo = grupoService.buscarOuFalhar(grupoId);

		usuario.adicionarGrupo(grupo);
	}

	public Usuario buscarOuFalhar(Long usuarioId) {
		return usuarioRepository.findById(usuarioId).orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
	}

	private void validarUsuario(Usuario usuario) {
		Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());

		if (usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
			throw new EmailJaCadastradoException();
		}

		usuarioExistente = usuarioRepository.findByCpf(usuario.getCpf());

		if (usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
			throw new CpfJaCadastradoException();
		}
	}
}

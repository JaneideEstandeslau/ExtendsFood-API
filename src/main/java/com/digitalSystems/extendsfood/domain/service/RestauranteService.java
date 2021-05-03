package com.digitalSystems.extendsfood.domain.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalSystems.extendsfood.domain.exception.CNPJJaCadastradoException;
import com.digitalSystems.extendsfood.domain.exception.RestauranteNaoEncontradoException;
import com.digitalSystems.extendsfood.domain.model.Cidade;
import com.digitalSystems.extendsfood.domain.model.Cozinha;
import com.digitalSystems.extendsfood.domain.model.FormaPagamento;
import com.digitalSystems.extendsfood.domain.model.Restaurante;
import com.digitalSystems.extendsfood.domain.model.Usuario;
import com.digitalSystems.extendsfood.domain.repository.RestauranteRepository;

@Service
public class RestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CozinhaService cozinhaService;
	
	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private FormaPagamentoService formaPagamentoService;
	
	@Autowired
	private UsuarioService usuarioService;

	@Transactional
	public Restaurante salvar(Restaurante restaurante) {

		restauranteRepository.detach(restaurante);
		
		validarCNPJ(restaurante);

		Long cozinhaId = restaurante.getCozinha().getId();
		Long cidadeId = restaurante.getEndereco().getCidade().getId();

		Cozinha cozinha = cozinhaService.buscarOuFalhar(cozinhaId);
		Cidade cidade = cidadeService.buscarOuFalhar(cidadeId);

		restaurante.setCozinha(cozinha);
		restaurante.getEndereco().setCidade(cidade);

		return restauranteRepository.save(restaurante);

	}

	@Transactional
	public void ativar(Long restauranteId) {
		Restaurante restaurante = buscarOuFalhar(restauranteId);

		restaurante.ativar();
	}

	@Transactional
	public void inativar(Long restauranteId) {
		Restaurante restaurante = buscarOuFalhar(restauranteId);

		restaurante.inativar();
	}
	
	@Transactional
	public void abrirFuncionamento(Long restauranteId) {
		Restaurante restaurante = buscarOuFalhar(restauranteId);

		restaurante.abrir();
		
		restauranteRepository.save(restaurante);
	}
	
	@Transactional
	public void fecharFuncionamento(Long restauranteId) {
		Restaurante restaurante = buscarOuFalhar(restauranteId);

		restaurante.fechar();
	}
	
	@Transactional
	public void desassociarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
		Restaurante restaurante = buscarOuFalhar(restauranteId);
		FormaPagamento formaPagamento = formaPagamentoService.buscarOuFalhar(formaPagamentoId);
		
		restaurante.removerFormaPagamento(formaPagamento);
	}
	
	@Transactional
	public void associarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
		Restaurante restaurante = buscarOuFalhar(restauranteId);
		FormaPagamento formaPagamento = formaPagamentoService.buscarOuFalhar(formaPagamentoId);
		
		restaurante.adicionarFormaPagamento(formaPagamento);
	}
	
	@Transactional
	public void desassociarResponsavel(Long restauranteId, Long usuarioId) {
	    Restaurante restaurante = buscarOuFalhar(restauranteId);
	    Usuario usuario = usuarioService.buscarOuFalhar(usuarioId);
	    
	    restaurante.removerResponsavel(usuario);
	}

	@Transactional
	public void associarResponsavel(Long restauranteId, Long usuarioId) {
	    Restaurante restaurante = buscarOuFalhar(restauranteId);
	    Usuario usuario = usuarioService.buscarOuFalhar(usuarioId);
	    
	    restaurante.adicionarResponsavel(usuario);
	}

	public Restaurante buscarOuFalhar(Long restauranteId) {
		return restauranteRepository.findById(restauranteId)
				.orElseThrow(() -> new RestauranteNaoEncontradoException(restauranteId));
	}

	private void validarCNPJ(Restaurante restaurante) {

		Optional<Restaurante> restauranteExistente = restauranteRepository.findByCnpj(restaurante.getCnpj());

		if (restauranteExistente.isPresent() && !restauranteExistente.get().equals(restaurante)) {
			throw new CNPJJaCadastradoException();
		}
	}
}

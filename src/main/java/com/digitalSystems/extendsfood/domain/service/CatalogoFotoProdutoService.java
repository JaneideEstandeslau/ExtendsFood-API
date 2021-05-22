package com.digitalSystems.extendsfood.domain.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.digitalSystems.extendsfood.domain.exception.FotoProdutoNaoEncontradoException;
import com.digitalSystems.extendsfood.domain.model.FotoProduto;
import com.digitalSystems.extendsfood.domain.repository.ProdutoRepository;
import com.digitalSystems.extendsfood.domain.service.FotoStorageService.NovaFoto;

@Service
public class CatalogoFotoProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private FotoStorageService fotoStorage;
	
	@Transactional
	public FotoProduto salvar(FotoProduto foto, InputStream dadosArquivo) throws IOException {
		Long categoriaId = foto.getCategoriaId();
		Long restauranteId = foto.getRestauranteId();
		Long produtoId = foto.getProduto().getId();
		
		Optional<FotoProduto> fotoExistente = produtoRepository
				.findFotoById(categoriaId, restauranteId, produtoId);
		
		String nomeNovoArquivo = fotoStorage.gerarNomeArquivo(foto.getNomeArquivo());
		String nomeArquivoExistente= null;
		
		
		if (fotoExistente.isPresent()) {
			nomeArquivoExistente = fotoExistente.get().getNomeArquivo();
			produtoRepository.delete(fotoExistente.get());
		}
		
		foto.setNomeArquivo(nomeNovoArquivo);
		foto =  produtoRepository.save(foto);
		produtoRepository.flush();
		
		NovaFoto novaFoto = NovaFoto.builder()
				.nomeAquivo(foto.getNomeArquivo())
				.contentType(foto.getContentType())
				.inputStream(dadosArquivo)
				.build();
				
		fotoStorage.substituir(nomeArquivoExistente, novaFoto);
		
		return foto;
	}
	
	@Transactional
	public void remover(Long categoriaId, Long restauranteId, Long produtoId) {
		
		FotoProduto fotoProduto = buscarOuFalhar(categoriaId, restauranteId, produtoId);
		
		produtoRepository.delete(fotoProduto);
		produtoRepository.flush();
		
		fotoStorage.remover(fotoProduto.getNomeArquivo());
	}
	
	public FotoProduto buscarOuFalhar(Long categoriaId, Long restauranteId, Long produtoId) {
		return produtoRepository.findFotoById(categoriaId, restauranteId, produtoId)
				.orElseThrow(() -> new FotoProdutoNaoEncontradoException(produtoId));
	}
}
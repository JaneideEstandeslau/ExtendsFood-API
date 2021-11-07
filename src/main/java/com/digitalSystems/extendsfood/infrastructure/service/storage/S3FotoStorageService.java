package com.digitalSystems.extendsfood.infrastructure.service.storage;

import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.digitalSystems.extendsfood.core.storage.StorageProperties;
import com.digitalSystems.extendsfood.domain.service.FotoStorageService;

public class S3FotoStorageService  implements FotoStorageService {

	@Autowired
	private AmazonS3 amazonS3;

	@Autowired
	private StorageProperties storageProperties;
	
	@Override
	public FotoRecuperada recuperar(String nomeArquivo) {
		String caminhoArquivo = getCaminhoArquivo(nomeArquivo);

		//Monta a URL de retorno sem a requisição 
		URL url = amazonS3.getUrl(storageProperties.getS3().getBucket(), caminhoArquivo);

		return FotoRecuperada.builder().url(url.toString()).build();
	}
	@Override
	public void armazenar(NovaFoto novaFoto) {
		try {
			
			//busca o caminho do arquivo
			String caminhoArquivo = getCaminhoArquivo(novaFoto.getNomeAquivo());

			//São os metadados de um objeto
			var objectMetadata = new ObjectMetadata();
			objectMetadata.setContentType(novaFoto.getContentType());//Adiciona o ContentType para a imagem ser exibida em uma nova 
																		//aba, ao invés de baixar a foto

			//Prepara a requisição na API da Amazon informando que estamos querendo adicionar um objeto
			var putObjectRequest = new PutObjectRequest(
					storageProperties.getS3().getBucket(), 
					caminhoArquivo,
					novaFoto.getInputStream(),
					objectMetadata)
					.withCannedAcl(CannedAccessControlList.PublicRead);//Adiciona a permissão de leitura publica na foto

			//Submete a requisição para a API da Amazon para adicionar o arquivo
			amazonS3.putObject(putObjectRequest);
			
		} catch (Exception e) {
			throw new StorageException("Não foi possível enviar arquivo para Amazon S3.", e);
		}
	}
	

	@Override
	public void remover(String nomeArquivo) {
		try {
			
			//busca o caminho do arquivo
			String caminhoArquivo = getCaminhoArquivo(nomeArquivo);

			//Prepara a requisição na API da Amazon para remover o arquivo
			var deleteObjectRequest = new DeleteObjectRequest(storageProperties.getS3().getBucket(), caminhoArquivo);
			
			//Submete a requisição para a API da Amazon para deletar o arquivo
			amazonS3.deleteObject(deleteObjectRequest);
			
		} catch (Exception e) {
			throw new StorageException("Não foi possível excluir arquivo para Amazon S3.", e);
		}
	}

	private String getCaminhoArquivo(String nomeArquivo) {
		return String.format("%s/%s", storageProperties.getS3().getDiretorioFotos(), nomeArquivo);
	}
}

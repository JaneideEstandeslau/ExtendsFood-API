package com.digitalSystems.extendsfood.core.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.digitalSystems.extendsfood.core.storage.StorageProperties.TipoStorage;
import com.digitalSystems.extendsfood.domain.service.FotoStorageService;
import com.digitalSystems.extendsfood.infrastructure.service.storage.LocalFotoStorageService;
import com.digitalSystems.extendsfood.infrastructure.service.storage.S3FotoStorageService;

@Configuration
public class StorageConfig {

	@Autowired
	private StorageProperties storageProperties;
	
	@Bean
	public AmazonS3 amazonS3() {
		//Credenciais para acessar o bucket da Amazon
		var credentials = new BasicAWSCredentials(
				storageProperties.getS3().getIdChaveAcesso(), //ID da chave de acesso
				storageProperties.getS3().getChaveAcessoSecreta());//Chave de acesso
		
		return AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials))//Adiciona as credenciais do acesso ao bucket
				.withRegion(storageProperties.getS3().getRegiao())//Adiciona a região do bucket 
				.build();
	}
	
	@Bean
	public FotoStorageService fotoStorageService() {
		if (TipoStorage.S3.equals(storageProperties.getTipo())) {
			return new S3FotoStorageService();
		} else {
			return new LocalFotoStorageService();
		}
	}
	
}
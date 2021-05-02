package com.digitalSystems.extendsfood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.digitalSystems.extendsfood.infrastructure.repository.CustomJpaRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class ExtendsfoodApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExtendsfoodApiApplication.class, args);
	}

}

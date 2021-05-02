package com.digitalSystems.extendsfood.domain.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.digitalSystems.extendsfood.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>{

	
	Optional<Restaurante> findByCnpj(String cnpj);
	
}

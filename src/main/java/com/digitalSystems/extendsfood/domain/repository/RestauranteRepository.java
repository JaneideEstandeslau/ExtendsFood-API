package com.digitalSystems.extendsfood.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.digitalSystems.extendsfood.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>{

	
	Optional<Restaurante> findByCnpj(String cnpj);
	
	@Query("select case when count(1) > 0 then true else false end"
			+ "	from Restaurante rest"
			+ "	join rest.responsaveis resp"
			+ "	where rest.id = :restauranteId"
			+ "	and resp.id = :usuarioId")
	boolean existsResponsavel(Long restauranteId, Long usuarioId);
	
}

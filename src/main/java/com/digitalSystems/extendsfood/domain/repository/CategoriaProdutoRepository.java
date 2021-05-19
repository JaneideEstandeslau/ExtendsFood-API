package com.digitalSystems.extendsfood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.digitalSystems.extendsfood.domain.model.CategoriaProduto;

@Repository
public interface CategoriaProdutoRepository extends JpaRepository<CategoriaProduto, Long>{

	
	@Query("from CategoriaProduto where restaurante.id = :restauranteId")
	List<CategoriaProduto> findTodosByRestaurante(Long restauranteId);
	
	@Query("from CategoriaProduto where restaurante.id = :restauranteId and id = :id")
	Optional<CategoriaProduto> findCategoriaByRestaurante(Long restauranteId, @Param("id") Long categoriaId);
}

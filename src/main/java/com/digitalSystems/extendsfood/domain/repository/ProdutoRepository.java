package com.digitalSystems.extendsfood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.digitalSystems.extendsfood.domain.model.CategoriaProduto;
import com.digitalSystems.extendsfood.domain.model.DiaSemana;
import com.digitalSystems.extendsfood.domain.model.FotoProduto;
import com.digitalSystems.extendsfood.domain.model.ItemComplemento;
import com.digitalSystems.extendsfood.domain.model.Produto;
import com.digitalSystems.extendsfood.domain.model.Restaurante;

@Repository
public interface ProdutoRepository
		extends JpaRepository<Produto, Long>, ProdutoRepositoryQueries, JpaSpecificationExecutor<Produto> {

	@Query("select p from Produto p join p.categoriaProduto c "
			+ "where p.id = :produto and c.id = :categoria and c.restaurante.id = :restaurante")
	Optional<Produto> findById(@Param("produto") Long produtoId, @Param("categoria") Long categoriaId, 
			@Param("restaurante") Long restauranteId);
	
	@Query("select p from Produto p join p.categoriaProduto c "
			+ "where p.disponivel = true and c = :categoriaProduto and c.restaurante = :restaurante")
	List<Produto> findByAtivoByCategoriaByRestaurante(CategoriaProduto categoriaProduto, Restaurante restaurante);
	
	@Query("select p from Produto p join p.categoriaProduto c "
			+ "where c.restaurante.id = :restaurante and p.id = :produto")
	Optional<Produto> findByRestaurante(@Param("restaurante") Long restauranteId, @Param("produto") Long produtoId);
	
	@Query("select f from FotoProduto f join f.produto p join p.categoriaProduto c "
			+ "where c.id = :categoria and c.restaurante.id = :restaurante"
			+ " and p.id = :produto")
	Optional<FotoProduto> findFotoById(@Param("categoria") Long categoriaId, @Param("restaurante") Long restauranteId,
			@Param("produto") Long produtoId);
	
	@Query("from ItemComplemento where id = :itemComplmentoId")
	Optional<ItemComplemento> findByItemComplementoByid(Long itemComplmentoId);
	
	@Query("from DiaSemana where id = :diaSemanaId")
	Optional<DiaSemana> findByDiaSemanaById(Long diaSemanaId);
	
	@Query("from DiaSemana d")
	List<DiaSemana> findByDiaSemana();
}

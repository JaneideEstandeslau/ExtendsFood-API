package com.digitalSystems.extendsfood.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.digitalSystems.extendsfood.domain.model.Pedido;

@Repository
public interface PedidoRepository
		extends CustomJpaRepository<Pedido, Long>, JpaSpecificationExecutor<Pedido>, PedidoRepositoryQueries {

	@Query("from Pedido p join fetch p.cliente join fetch p.restaurante r join fetch r.cozinha")
	List<Pedido> findAll();

	@Query("select case when count(1) > 0 then true else false end "
			+ "from Pedido ped "
			+ "join ped.restaurante rest "
			+ "join rest.responsaveis resp "
			+ "where ped.id = :codigoPedido and "
			+ "resp.id = :usuarioId")
	boolean isPedidoGerenciadoPor(Long codigoPedido, Long usuarioId);

}

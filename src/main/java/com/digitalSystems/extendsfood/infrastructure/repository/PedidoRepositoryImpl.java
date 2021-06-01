package com.digitalSystems.extendsfood.infrastructure.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.digitalSystems.extendsfood.domain.model.Pedido;
import com.digitalSystems.extendsfood.domain.model.Usuario;
import com.digitalSystems.extendsfood.domain.repository.PedidoRepositoryQueries;

@Repository
public class PedidoRepositoryImpl implements PedidoRepositoryQueries {

	@Autowired
	private EntityManager manager;
	
	@Override
	public List<Pedido> findByUsuario(Usuario cliente) {
		
		var builder = manager.getCriteriaBuilder();
		var query = builder.createQuery(Pedido.class);
		var root = query.from(Pedido.class);		
		
//		var functionDateDataCriacao = builder.function(
//				"date", Date.class, root.get("dataCriacao"));
		
		var predicates = new ArrayList<Predicate>();		
		predicates.add(builder.equal(root.get("cliente"), cliente));
		
		query.where(predicates.toArray(new Predicate[0]));
//		query.groupBy(functionDateDataCriacao);
		
		return manager.createQuery(query).getResultList();
	}

}

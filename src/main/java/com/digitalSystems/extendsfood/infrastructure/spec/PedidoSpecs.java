package com.digitalSystems.extendsfood.infrastructure.spec;

import java.util.ArrayList;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.data.jpa.domain.Specification;

import com.digitalSystems.extendsfood.domain.filter.PedidoFilter;
import com.digitalSystems.extendsfood.domain.model.ItemPedido;
import com.digitalSystems.extendsfood.domain.model.Pedido;
import com.digitalSystems.extendsfood.domain.model.Produto;

public class PedidoSpecs {

	public static Specification<Pedido> filtrarPedidos(PedidoFilter filtro) {
		
		System.out.println("Test");
		return (root, query, builder) -> {
            
			if (Pedido.class.equals(query.getResultType())) {
				root.fetch("restaurante").fetch("cozinha");
				root.fetch("cliente");
		    }
			
			var predicates = new ArrayList<Predicate>();
			
			if (filtro.getProdutoId() != null) {
				
				Subquery<Long> subQuery = query.subquery(Long.class);
	            Root<ItemPedido> from = subQuery.from(ItemPedido.class);	            

	            Join<ItemPedido, Produto> produto = from.join("produto");
	            Join<ItemPedido, Pedido> pedido = from.join("pedido");
	            
	            subQuery.select(pedido.get("id"));
	            subQuery.where(builder.equal(produto, filtro.getProdutoId()));
	            
	            predicates.add(root.get("id").in(subQuery));
	           
			}
			
			if(filtro.getStatus() != null) {
				predicates.add(builder.equal(root.get("status"), filtro.getStatus()));
			}
			
			if (filtro.getRestauranteId() != null) {
				predicates.add(builder.equal(root.get("restaurante"), filtro.getRestauranteId()));
			}
			
			if (filtro.getDataCriacaoInicio() != null) {
				predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"), 
						filtro.getDataCriacaoInicio()));
			}
			
			if (filtro.getDataCriacaoFim() != null) {
				predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"), 
						filtro.getDataCriacaoFim()));
			}
			
			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}
	
}
package com.digitalSystems.extendsfood.infrastructure.spec;

import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.digitalSystems.extendsfood.domain.model.CategoriaProduto;

public class CategoriaProdutoSpecs {

	public static Specification<CategoriaProduto> peginarCategoriasDosProdutos(Long restauranteId) {
		return (root, query, builder) -> {

			if (CategoriaProduto.class.equals(query.getResultType())) {
				root.fetch("restaurante");
			}

			var predicates = new ArrayList<Predicate>();

			predicates.add(builder.equal(root.get("restaurante"), restauranteId));

			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}
}

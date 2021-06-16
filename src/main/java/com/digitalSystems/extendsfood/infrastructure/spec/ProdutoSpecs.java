package com.digitalSystems.extendsfood.infrastructure.spec;

import java.util.ArrayList;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.digitalSystems.extendsfood.domain.model.CategoriaProduto;
import com.digitalSystems.extendsfood.domain.model.Produto;
import com.digitalSystems.extendsfood.domain.model.Restaurante;

public class ProdutoSpecs {

	public static Specification<Produto> buscarProdutosdaCategoriaDoRestaurante(Long restauranteId, Long categoriaId) {
		return (root, query, builder) -> {

			Join<Produto, CategoriaProduto> categoriaPorduto = root.join("categoriaProduto");
			Join<CategoriaProduto, Restaurante> restaurante = categoriaPorduto.join("restaurante");

			var predicates = new ArrayList<Predicate>();

			predicates.add(builder.equal(restaurante, restauranteId));
			predicates.add(builder.equal(categoriaPorduto, categoriaId));

			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}
}

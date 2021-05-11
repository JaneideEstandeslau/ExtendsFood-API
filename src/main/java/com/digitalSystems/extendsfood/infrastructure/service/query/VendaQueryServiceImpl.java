package com.digitalSystems.extendsfood.infrastructure.service.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.digitalSystems.extendsfood.domain.filter.VendaFilter;
import com.digitalSystems.extendsfood.domain.model.FormaPagamento;
import com.digitalSystems.extendsfood.domain.model.Pedido;
import com.digitalSystems.extendsfood.domain.model.Restaurante;
import com.digitalSystems.extendsfood.domain.model.dto.VendaDiaria;
import com.digitalSystems.extendsfood.domain.model.enums.StatusPedido;
import com.digitalSystems.extendsfood.domain.service.VendaQueryService;

@Repository
public class VendaQueryServiceImpl implements VendaQueryService {

	@Autowired
	private EntityManager manager;
	
	@Override
	public List<VendaDiaria> consultarVendas(VendaFilter filtro) {
		var builder = manager.getCriteriaBuilder();				// Cria um builder para contruir a criteria
		var query = builder.createQuery(VendaDiaria.class);		// Informa o TIPO DE RETORNO da consulta criteria
		var root = query.from(Pedido.class);					// cláusula FROM é Pedido
		Join<Pedido, FormaPagamento> formaPagamento = root.join("formaPagamento");
		Join<Pedido, Restaurante> restaurante = root.join("restaurante");
		
		var predicates = new ArrayList<Predicate>();
		
		// O function cria a função "date" pra ser utilizada no SQL do banco de dados.
		var functionDateDataCriacao = builder.function(
				"date", Date.class, root.get("dataCriacao"));
		
		// Cria uma seleção correspondendo a um construtor
		// O resultado da pesquisa será usado pra chamar o construtor de uma classe
		// "Construa VendaDiaria a partir da seleção
		var selection = builder.construct(VendaDiaria.class,
				functionDateDataCriacao,
				restaurante.get("nome"),
				root.get("status"),
				formaPagamento.get("descricao"),
				builder.count(root.get("id")),
				builder.sum(root.get("valorTotal")));
		
		
		//Adiciona as restrições 
		if(filtro.getRestauranteId() != null) {
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
		
		predicates.add(root.get("status").in(StatusPedido.CONFIRMADO, StatusPedido.ENTREGUE));
		
		
		query.select(selection);
		query.where(predicates.toArray(new Predicate[0]));
		query.groupBy(functionDateDataCriacao);
		
		return manager.createQuery(query).getResultList();
	}
	
}
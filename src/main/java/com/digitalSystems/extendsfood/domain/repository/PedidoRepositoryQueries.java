package com.digitalSystems.extendsfood.domain.repository;

import java.util.List;

import com.digitalSystems.extendsfood.domain.model.Pedido;
import com.digitalSystems.extendsfood.domain.model.Usuario;

public interface PedidoRepositoryQueries {
	
	List<Pedido> findByUsuario(Usuario cliente);

}

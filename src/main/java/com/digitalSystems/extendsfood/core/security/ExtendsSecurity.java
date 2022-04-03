package com.digitalSystems.extendsfood.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import com.digitalSystems.extendsfood.domain.repository.PedidoRepository;
import com.digitalSystems.extendsfood.domain.repository.RestauranteRepository;

@Component
public class ExtendsSecurity {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	private Authentication getAuthentication() {
		// Pega o contexto atual de segurança
		// Retorna o Token
		return SecurityContextHolder.getContext().getAuthentication();
	}

	public Long getUsuarioId() {
		Jwt jwt = (Jwt) getAuthentication().getPrincipal();
		return jwt.getClaim("usuario_id");
	}

	public boolean gerenciaRestaurante(Long restauranteId) {

		if (restauranteId != null) {
			return restauranteRepository.existsResponsavel(restauranteId, getUsuarioId());
		}

		return false;
	}

	public boolean gerenciaRestauranteDoPedido(Long codigoPedido) {
		return pedidoRepository.isPedidoGerenciadoPor(codigoPedido, getUsuarioId());
	}

	public boolean usuarioAutenticadoIgual(Long usuarioId) {
		return getUsuarioId() != null && usuarioId != null && getUsuarioId().equals(usuarioId);
	}

	public boolean hasAuthority(String authorityName) {
		return  getAuthentication().getAuthorities().stream()
				.anyMatch(authority -> authority.getAuthority().equals(authorityName));
	}

	public boolean podeGerenciarPedidos(Long pedidoId) {
		return hasAuthority("SCOPE_WRITE") && (hasAuthority("GERENCIAR_PEDIDOS") 
				|| gerenciaRestauranteDoPedido(pedidoId));
	}

}

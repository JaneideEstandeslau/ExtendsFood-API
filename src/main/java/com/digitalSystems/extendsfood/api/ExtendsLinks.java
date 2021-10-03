package com.digitalSystems.extendsfood.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariable.VariableType;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.stereotype.Component;

import com.digitalSystems.extendsfood.api.controller.CidadeController;
import com.digitalSystems.extendsfood.api.controller.EstadoController;
import com.digitalSystems.extendsfood.api.controller.FluxoPedidoController;
import com.digitalSystems.extendsfood.api.controller.FormaPagamentoController;
import com.digitalSystems.extendsfood.api.controller.PedidoController;
import com.digitalSystems.extendsfood.api.controller.RestauranteCategoriaProdutoController;
import com.digitalSystems.extendsfood.api.controller.RestauranteController;
import com.digitalSystems.extendsfood.api.controller.UsuarioController;

@Component
public class ExtendsLinks {

	public static final TemplateVariables PAGINACAO_VARIABLES = new TemplateVariables(
			new TemplateVariable("page", VariableType.REQUEST_PARAM),
			new TemplateVariable("size", VariableType.REQUEST_PARAM),
			new TemplateVariable("sort", VariableType.REQUEST_PARAM));

	//PEDIDO		
	public Link linkToPedidos() {
		TemplateVariables filtroVariables = new TemplateVariables(
				new TemplateVariable("clienteId", VariableType.REQUEST_PARAM),
				new TemplateVariable("restauranteId", VariableType.REQUEST_PARAM),
				new TemplateVariable("dataCriacaoInicio", VariableType.REQUEST_PARAM),
				new TemplateVariable("dataCriacaoFim", VariableType.REQUEST_PARAM));

		String pedidosUrl = linkTo(PedidoController.class).toUri().toString();

		return new Link(UriTemplate.of(pedidosUrl, PAGINACAO_VARIABLES.concat(filtroVariables)), "pedidos");
	}
	
	public Link linkToConfirmacaoPedido(Long pedidoId, String rel) {
		return linkTo(methodOn(FluxoPedidoController.class).confirmarPedido(pedidoId)).withRel(rel);
	}
	
	public Link linkToPedidoSaiuParaEntrega(Long pedidoId, String rel) {
		return linkTo(methodOn(FluxoPedidoController.class).pedidoSaiuParaEntrega(pedidoId)).withRel(rel);
	}
	
	public Link linkToEntregarPedido(Long pedidoId, String rel) {
		return linkTo(methodOn(FluxoPedidoController.class).entregarPedido(pedidoId)).withRel(rel);
	}
	
	public Link linkToCancelarPedido(Long pedidoId, String rel) {
		return linkTo(methodOn(FluxoPedidoController.class).cancelarPedido(pedidoId)).withRel(rel);
	}

	// RESTAURANTE
	public Link linkToRestaurante(Long restauranteId, String rel) {
		return linkTo(methodOn(RestauranteController.class).buscar(restauranteId)).withRel(rel);
	}

	public Link linkToRestaurante(Long restauranteId) {
		return linkToRestaurante(restauranteId, IanaLinkRelations.SELF.value());
	}

	// FORMA DE PAGAMENTO
	public Link linkToFormaPagamento(Long formaPagamentoId, String rel) {
		return linkTo(methodOn(FormaPagamentoController.class).buscar(formaPagamentoId)).withRel(rel);
	}

	public Link linkToFormaPagamento(Long formaPagamentoId) {
		return linkToFormaPagamento(formaPagamentoId, IanaLinkRelations.SELF.value());
	}

	// USUÁRIO
	public Link linkToUsuario(Long usuarioId, String rel) {
		return linkTo(methodOn(UsuarioController.class).buscar(usuarioId)).withSelfRel();
	}

	public Link linkToUsuario(Long usuarioId) {
		return linkToUsuario(usuarioId, IanaLinkRelations.SELF.value());
	}

	// CIDADE
	public Link linkToCidade(Long cidadeId, String rel) {
		return linkTo(methodOn(CidadeController.class).buscar(cidadeId)).withRel(rel);
	}

	public Link linkToCidade(Long cidadeId) {
		return linkToCidade(cidadeId, IanaLinkRelations.SELF.value());
	}

	//PRODUTO
	public Link linkToProduto(Long restauranteId, Long categoriaId, Long produtoId, String rel) {
		return linkTo(methodOn(RestauranteCategoriaProdutoController.class)
				.buscarProdutoCategoriaRestaurante(restauranteId, categoriaId, produtoId)).withRel(rel);
	}
	
	public Link linkToProduto(Long restauranteId, Long categoriaId, Long produtoId) {
		return linkToProduto(restauranteId, categoriaId, produtoId, IanaLinkRelations.SELF.value());
	}
	
	//ESTADOS
	public Link linkToEstados(String rel) {
		return linkTo(EstadoController.class).withRel(rel);
	}
	
	public Link linkToEstados() {
	    return linkToEstados(IanaLinkRelations.SELF.value());
	}

}
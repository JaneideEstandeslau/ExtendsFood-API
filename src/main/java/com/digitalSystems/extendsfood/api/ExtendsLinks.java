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

import com.digitalSystems.extendsfood.api.controller.CategoriaProdutoController;
import com.digitalSystems.extendsfood.api.controller.CidadeController;
import com.digitalSystems.extendsfood.api.controller.CozinhaController;
import com.digitalSystems.extendsfood.api.controller.EstadoController;
import com.digitalSystems.extendsfood.api.controller.EstatisticasController;
import com.digitalSystems.extendsfood.api.controller.FluxoPedidoController;
import com.digitalSystems.extendsfood.api.controller.FormaPagamentoController;
import com.digitalSystems.extendsfood.api.controller.GrupoController;
import com.digitalSystems.extendsfood.api.controller.GrupoPermissaoController;
import com.digitalSystems.extendsfood.api.controller.PedidoController;
import com.digitalSystems.extendsfood.api.controller.RestauranteCategoriaProdutoController;
import com.digitalSystems.extendsfood.api.controller.RestauranteController;
import com.digitalSystems.extendsfood.api.controller.RestauranteFormaPagamentoController;
import com.digitalSystems.extendsfood.api.controller.RestauranteProdutoFotoController;
import com.digitalSystems.extendsfood.api.controller.RestauranteUsuarioResponsavelController;
import com.digitalSystems.extendsfood.api.controller.UsuarioController;
import com.digitalSystems.extendsfood.api.controller.UsuarioGrupoController;

@Component
public class ExtendsLinks {

	public static final TemplateVariables PAGINACAO_VARIABLES = new TemplateVariables(
			new TemplateVariable("page", VariableType.REQUEST_PARAM),
			new TemplateVariable("size", VariableType.REQUEST_PARAM),
			new TemplateVariable("sort", VariableType.REQUEST_PARAM));

	// PEDIDO
	public Link linkToPedidos(String rel) {
		TemplateVariables filtroVariables = new TemplateVariables(
				new TemplateVariable("clienteId", VariableType.REQUEST_PARAM),
				new TemplateVariable("restauranteId", VariableType.REQUEST_PARAM),
				new TemplateVariable("dataCriacaoInicio", VariableType.REQUEST_PARAM),
				new TemplateVariable("dataCriacaoFim", VariableType.REQUEST_PARAM));

		String pedidosUrl = linkTo(PedidoController.class).toUri().toString();

		return new Link(UriTemplate.of(pedidosUrl, PAGINACAO_VARIABLES.concat(filtroVariables)), rel);
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

	public Link linkToRestaurantes(String rel) {
		return linkTo(RestauranteController.class).withRel(rel);
	}

	public Link linkToRestauranteFormasPagamento(Long restauranteId, String rel) {
		return linkTo(methodOn(RestauranteFormaPagamentoController.class).listar(restauranteId)).withRel(rel);
	}

	public Link linkToRestauranteFormasPagamentoDesassociacao(Long restauranteId, Long formaPagamentoId, String rel) {

		return linkTo(methodOn(RestauranteFormaPagamentoController.class).desassociar(restauranteId, formaPagamentoId))
				.withRel(rel);
	}

	public Link linkToRestauranteFormasPagamentoAssociar(Long restauranteId, String rel) {

		return linkTo(methodOn(RestauranteFormaPagamentoController.class).associar(restauranteId, null)).withRel(rel);
	}

	public Link linkToRestaurante(Long restauranteId) {
		return linkToRestaurante(restauranteId, IanaLinkRelations.SELF.value());
	}

	public Link linkToRestauranteResponsaveis(Long restauranteId) {
		return linkToRestauranteResponsaveis(restauranteId, IanaLinkRelations.SELF.value());
	}

	public Link linkToRestauranteResponsaveis(Long restauranteId, String rel) {
		return linkTo(methodOn(RestauranteUsuarioResponsavelController.class).listar(restauranteId)).withRel(rel);
	}

	public Link linkToRestaurantes() {
		return linkToRestaurantes(IanaLinkRelations.SELF.value());
	}

	public Link linkToAtivarRestaurante(Long restauranteId, String rel) {
		return linkTo(methodOn(RestauranteController.class).ativar(restauranteId)).withRel(rel);
	}

	public Link linkToInativarRestaurante(Long restauranteId, String rel) {
		return linkTo(methodOn(RestauranteController.class).inativar(restauranteId)).withRel(rel);
	}

	public Link linkToAbrirRestaurante(Long restauranteId, String rel) {
		return linkTo(methodOn(RestauranteController.class).abrirFuncionamento(restauranteId)).withRel(rel);
	}

	public Link linkToFecharRestaurante(Long restauranteId, String rel) {
		return linkTo(methodOn(RestauranteController.class).fecharFuncionamento(restauranteId)).withRel(rel);
	}

	// FORMA DE PAGAMENTO
	public Link linkToFormaPagamento(Long formaPagamentoId, String rel) {
		return linkTo(methodOn(FormaPagamentoController.class).buscar(formaPagamentoId)).withRel(rel);
	}

	public Link linkToFormaPagamento(Long formaPagamentoId) {
		return linkToFormaPagamento(formaPagamentoId, IanaLinkRelations.SELF.value());
	}

	public Link linkToFormasPagamento(String rel) {
		return linkTo(FormaPagamentoController.class).withRel(rel);
	}

	public Link linkToFormasPagamento() {
		return linkToFormasPagamento(IanaLinkRelations.SELF.value());
	}

	public Link linkToRestauranteFormasPagamento(Long restauranteId) {
		return linkToRestauranteFormasPagamento(restauranteId, IanaLinkRelations.SELF.value());
	}

	// USUÁRIO
	public Link linkToUsuario(Long usuarioId, String rel) {
		return linkTo(methodOn(UsuarioController.class).buscar(usuarioId)).withRel(rel);
	}

	public Link linkToUsuario(Long usuarioId) {
		return linkToUsuario(usuarioId, IanaLinkRelations.SELF.value());
	}

	public Link linkToUsuarios(String rel) {
		return linkTo(UsuarioController.class).withRel(rel);
	}

	public Link linkToUsuarios() {
		return linkToUsuarios(IanaLinkRelations.SELF.value());
	}

	public Link linkToGruposUsuario(Long usuarioId, String rel) {
		return linkTo(methodOn(UsuarioGrupoController.class).listar(usuarioId)).withRel(rel);
	}

	public Link linkToGruposUsuario(Long usuarioId) {
		return linkToGruposUsuario(usuarioId, IanaLinkRelations.SELF.value());
	}

	public Link linkToRestauranteResponsavelAssociacao(Long restauranteId) {
		return linkTo(methodOn(RestauranteUsuarioResponsavelController.class).associar(restauranteId, null))
				.withRel("associar");
	}

	public Link linkToRestauranteResponsavelDesassociacao(Long restauranteId, Long usuarioId) {
		return linkTo(methodOn(RestauranteUsuarioResponsavelController.class).desassociar(restauranteId, usuarioId))
				.withRel("associar");
	}

	// COZINHA
	public Link linkToCozinha(Long cozinhaId, String rel) {
		return linkTo(methodOn(CozinhaController.class).buscar(cozinhaId)).withRel(rel);
	}

	public Link linkToCozinha(Long cozinhaId) {
		return linkToCozinha(cozinhaId, IanaLinkRelations.SELF.value());
	}

	public Link linkToCozinhas(String rel) {
		return linkTo(CozinhaController.class).withRel(rel);
	}

	public Link linkToCozinhas() {
		return linkToCozinhas(IanaLinkRelations.SELF.value());
	}

	// CIDADE
	public Link linkToCidade(Long cidadeId, String rel) {
		return linkTo(methodOn(CidadeController.class).buscar(cidadeId)).withRel(rel);
	}

	public Link linkToCidade(Long cidadeId) {
		return linkToCozinha(cidadeId, IanaLinkRelations.SELF.value());
	}

	public Link linkToCidades(String rel) {
		return linkTo(CidadeController.class).withRel(rel);
	}

	public Link linkToCidades() {
		return linkToCidades(IanaLinkRelations.SELF.value());
	}

	// PRODUTO
	public Link linkToProduto(Long restauranteId, Long categoriaId, Long produtoId, String rel) {
		return linkTo(methodOn(RestauranteCategoriaProdutoController.class)
				.buscarProdutoCategoriaRestaurante(restauranteId, categoriaId, produtoId)).withRel(rel);
	}

	public Link linkToProduto(Long restauranteId, Long categoriaId, Long produtoId) {
		return linkToProduto(restauranteId, categoriaId, produtoId, IanaLinkRelations.SELF.value());
	}

	public Link linkToRestauranteCategoria(Long restauranteId, Long categoriaId) {

		return linkToRestauranteCategoria(restauranteId, categoriaId, IanaLinkRelations.SELF.value());
	}

	public Link linkToRestauranteCategoria(Long restauranteId, Long categoriaId, String rel) {

		String pedidosUrl = linkTo(
				methodOn(RestauranteCategoriaProdutoController.class).listar(restauranteId, categoriaId, null)).toUri()
						.toString();

		return new Link(UriTemplate.of(pedidosUrl, PAGINACAO_VARIABLES), rel);
	}

	// ESTADOS
	public Link linkToEstado(Long estadoId, String rel) {
		return linkTo(methodOn(EstadoController.class).buscar(estadoId)).withRel(rel);
	}

	public Link linkToEstado(Long estadoId) {
		return linkToEstado(estadoId, IanaLinkRelations.SELF.value());
	}

	public Link linkToEstados(String rel) {
		return linkTo(EstadoController.class).withRel(rel);
	}

	public Link linkToEstados() {
		return linkToEstados(IanaLinkRelations.SELF.value());
	}

	// Categoria Produto

	public Link linkToCategoriaProduto(Long restauranteId, Long categoriaProdutoId, String rel) {
		return linkTo(methodOn(CategoriaProdutoController.class).buscar(restauranteId, categoriaProdutoId))
				.withRel(rel);
	}

	public Link linkToCategoriaProduto(Long restauranteId, Long categoriaProdutoId) {
		return linkTo(methodOn(CategoriaProdutoController.class).buscar(restauranteId, categoriaProdutoId))
				.withRel(IanaLinkRelations.SELF.value());
	}

	public Link linkToCategoriaProdutos(Long restauranteId, String rel) {

		String pedidosUrl = linkTo(methodOn(CategoriaProdutoController.class).listar(restauranteId, null)).toUri()
				.toString();

		return new Link(UriTemplate.of(pedidosUrl, PAGINACAO_VARIABLES), rel);
	}

	// Foto Poduto

	public Link linkToFotoProduto(Long restauranteId, Long categoriaId, Long produtoId, String rel) {
		return linkTo(methodOn(RestauranteProdutoFotoController.class).buscar(restauranteId, categoriaId, produtoId))
				.withRel(rel);
	}

	public Link linkToFotoProduto(Long restauranteId, Long categoriaId, Long produtoId) {
		return linkToFotoProduto(restauranteId, categoriaId, produtoId, IanaLinkRelations.SELF.value());
	}

	// Grupo

	public Link linkToGrupos() {
		return linkToGrupos(IanaLinkRelations.SELF.value());
	}

	public Link linkToGrupos(String rel) {
		return linkTo(GrupoController.class).withRel(rel);
	}

	public Link linkToAssociarUsuarioAoGrupo(Long usuarioId, String rel) {
		return linkTo(methodOn(UsuarioGrupoController.class).associar(usuarioId, null)).withRel(rel);
	}

	public Link linkToDesassociarUsuarioAoGrupo(Long usuarioId, Long grupoId, String rel) {
		return linkTo(methodOn(UsuarioGrupoController.class).desassociar(usuarioId, grupoId)).withRel(rel);
	}

	// Permissao

	public Link linkToGrupoPermissao(Long grupoId, String rel) {
		return linkTo(methodOn(GrupoPermissaoController.class).listar(grupoId)).withRel(rel);
	}

	public Link linkToGrupoPermissao(Long grupoId) {
		return linkToGrupoPermissao(grupoId, IanaLinkRelations.SELF.value());
	}

	public Link linkToGrupoPermissaoAssociacao(Long grupoId, String rel) {
		return linkTo(methodOn(GrupoPermissaoController.class).associar(grupoId, null)).withRel(rel);
	}

	public Link linkToGrupoPermissaoDesassociacao(Long grupoId, Long permissaoId, String rel) {
		return linkTo(methodOn(GrupoPermissaoController.class).desassociar(grupoId, permissaoId)).withRel(rel);
	}

	// Estatisticas

	public Link linkToEstatisticas(String rel) {
		return linkTo(EstatisticasController.class).withRel(rel);
	}

	public Link linkToEstatisticasVendasDiarias(String rel) {
		TemplateVariables filtroVariables = new TemplateVariables(
				new TemplateVariable("restauranteId", VariableType.REQUEST_PARAM),
				new TemplateVariable("dataCriacaoInicio", VariableType.REQUEST_PARAM),
				new TemplateVariable("dataCriacaoFim", VariableType.REQUEST_PARAM),
				new TemplateVariable("timeOffset", VariableType.REQUEST_PARAM));

		String pedidosUrl = linkTo(methodOn(EstatisticasController.class).consultarVendas(null)).toUri().toString();

		return new Link(UriTemplate.of(pedidosUrl, filtroVariables), rel);
	}

}
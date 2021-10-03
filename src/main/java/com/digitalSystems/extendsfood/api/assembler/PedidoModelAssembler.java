package com.digitalSystems.extendsfood.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.digitalSystems.extendsfood.api.ExtendsLinks;
import com.digitalSystems.extendsfood.api.controller.PedidoController;
import com.digitalSystems.extendsfood.api.model.PedidoModel;
import com.digitalSystems.extendsfood.domain.model.Pedido;

@Component
public class PedidoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ExtendsLinks extendsLinks;

	public PedidoModelAssembler() {
		super(PedidoController.class, PedidoModel.class);
	}

	@Override
	public PedidoModel toModel(Pedido pedido) {

		PedidoModel pedidoModel = createModelWithId(pedido.getId(), pedido);

		modelMapper.map(pedido, pedidoModel);

		// Adiciona a URI para encontrar todos os pedidos do Restaurante
		pedidoModel.add(extendsLinks.linkToPedidos());
		
		//adiciona as URIs para mudar o status di pedido
		if(pedido.podeSerConfirmado()) {
			pedidoModel.add(extendsLinks.linkToConfirmacaoPedido(pedido.getId(), "Confirmar"));			
		}
		if(pedido.podeSairParaEntrega()) {
			pedidoModel.add(extendsLinks.linkToPedidoSaiuParaEntrega(pedido.getId(), "Saiu para entrega"));			
		}
		if(pedido.podeSerEntregue()) {
			pedidoModel.add(extendsLinks.linkToEntregarPedido(pedido.getId(), "Entregar"));			
		}
		if(pedido.podeSerCancelado()) {
			pedidoModel.add(extendsLinks.linkToCancelarPedido(pedido.getId(), "Cancelar"));			
		}

		// Adiciona a URI para encontrar o recurso de Restaurante
		pedidoModel.getRestaurante().add(extendsLinks.linkToRestaurante(pedido.getRestaurante().getId()));

		// Adiciona a URI para encontrar o recurso de Forma de Pagamento
		pedidoModel.getFormaPagamento().add(extendsLinks.linkToFormaPagamento(pedido.getFormaPagamento().getId()));

		// Adiciona a URI para encontrar o recurso de Cliente
		pedidoModel.getCliente().add(extendsLinks.linkToUsuario(pedidoModel.getCliente().getId()));

		// Adiciona a URI para encontrar o recurso de Endereço
		pedidoModel.getEndereco().getCidade()
				.add(extendsLinks.linkToCidade(pedidoModel.getEndereco().getCidade().getId()));

		// Adiciona a URI para encontrar os recursos de de Item do Pedido e complemento,
		// que estão no recurso de Produto
		pedidoModel.getItensPedido().forEach(itemPedido -> {

			itemPedido.add(extendsLinks.linkToProduto(pedido.getRestaurante().getId(), 1L, itemPedido.getProdutoId()));

			itemPedido.getItensComplemento().forEach(complemento -> {

				complemento.removeLinks();

				complemento.add(
						extendsLinks.linkToProduto(pedido.getRestaurante().getId(), 1L, itemPedido.getProdutoId()));

			});
		});

		return pedidoModel;
	}

	public List<PedidoModel> toCollectionModel(Collection<Pedido> pedidos) {
		return pedidos.stream().map(pedido -> toModel(pedido)).collect(Collectors.toList());
	}
}

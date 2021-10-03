package com.digitalSystems.extendsfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.digitalSystems.extendsfood.api.ExtendsLinks;
import com.digitalSystems.extendsfood.api.controller.PedidoController;
import com.digitalSystems.extendsfood.api.model.PedidoResumoModel;
import com.digitalSystems.extendsfood.domain.model.Pedido;

@Component
public class PedidoResumoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ExtendsLinks extendsLinks;

	public PedidoResumoModelAssembler() {
		super(PedidoController.class, PedidoResumoModel.class);
	}

	@Override
	public PedidoResumoModel toModel(Pedido pedido) {

		PedidoResumoModel pedidoResumoModel = createModelWithId(pedido.getId(), pedido);

		modelMapper.map(pedido, pedidoResumoModel);

		pedidoResumoModel.getRestaurante().add(extendsLinks.linkToRestaurante(pedido.getRestaurante().getId()));

		pedidoResumoModel.getCliente().add(extendsLinks.linkToUsuario(pedidoResumoModel.getCliente().getId()));

		pedidoResumoModel.getItensPedido().forEach(itemPedido -> {

			itemPedido.add(extendsLinks.linkToProduto(pedido.getRestaurante().getId(), 1L, itemPedido.getProdutoId(),
					"produto"));
		});
		return pedidoResumoModel;
	}
}

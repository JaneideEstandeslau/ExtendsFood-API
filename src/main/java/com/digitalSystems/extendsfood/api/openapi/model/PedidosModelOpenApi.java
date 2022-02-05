package com.digitalSystems.extendsfood.api.openapi.model;
import java.util.List;

import org.springframework.hateoas.Links;

import com.digitalSystems.extendsfood.api.model.PedidoResumoModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("PedidosModel")
@Data
public class PedidosModelOpenApi {

	private PedidosEmbeddedModelOpenApi _embedded;
	private Links _links;
	private PageModelOpenApi page;

	@ApiModel("PedidosEmbeddedModel")
	@Data
	public class PedidosEmbeddedModelOpenApi {

		private List<PedidoResumoModel> pedidos;

	}

}
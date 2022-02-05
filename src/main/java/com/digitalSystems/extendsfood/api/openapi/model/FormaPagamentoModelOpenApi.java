package com.digitalSystems.extendsfood.api.openapi.model;
import java.util.List;

import org.springframework.hateoas.Links;

import com.digitalSystems.extendsfood.api.model.FormaPagamentoModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("FormasPagamentoModel")
@Data
public class FormaPagamentoModelOpenApi {

	private FormaPagamentoEmbeddedModelOpenApi _embedded;
	private Links _links;

	@ApiModel("FormasPagamentoEmbeddedModel")
	@Data
	public class FormaPagamentoEmbeddedModelOpenApi {

		private List<FormaPagamentoModel> formasPagamento;

	}

}
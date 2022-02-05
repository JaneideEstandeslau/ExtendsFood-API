package com.digitalSystems.extendsfood.api.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.digitalSystems.extendsfood.api.model.CategoriaProdutoResumoModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@ApiModel("CategoriasProdutoResumoModel")
@Getter
@Setter
public class CategoriasProdutoResumoModelOpenApi {

	private CategoriasEmbeddedModelOpenApi _embedded;
	private Links _links;
	private PageModelOpenApi page;

	@ApiModel("CategoriasEmbeddedModel")
	@Data
	public class CategoriasEmbeddedModelOpenApi {

		private List<CategoriaProdutoResumoModel> categorias;

	}
}

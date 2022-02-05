package com.digitalSystems.extendsfood.api.openapi.model;
import java.util.List;

import org.springframework.hateoas.Links;

import com.digitalSystems.extendsfood.api.model.UsuarioModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("UsuariosoModel")
@Data
public class UsuariosModelOpenApi {

	private UsuariosModelEmbeddedModelOpenApi _embedded;
	private Links _links;

	@ApiModel("UsuariosModelEmbeddedModel")
	@Data
	public class UsuariosModelEmbeddedModelOpenApi {

		private List<UsuarioModel> usuarios;

	}

}
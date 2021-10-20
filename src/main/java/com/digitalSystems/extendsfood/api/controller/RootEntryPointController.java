package com.digitalSystems.extendsfood.api.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitalSystems.extendsfood.api.ExtendsLinks;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntryPointController {

	@Autowired
	private ExtendsLinks extendsLinks;
	
	@GetMapping
	public RootEntryPointModel root() {
		var rootEntryPointModel = new RootEntryPointModel();
		
		rootEntryPointModel.add(extendsLinks.linkToCozinhas("cozinhas"));
		rootEntryPointModel.add(extendsLinks.linkToPedidos("pedidos"));
		rootEntryPointModel.add(extendsLinks.linkToRestaurantes("restaurantes"));
		rootEntryPointModel.add(extendsLinks.linkToGrupos("grupos"));
		rootEntryPointModel.add(extendsLinks.linkToUsuarios("usuarios"));
		rootEntryPointModel.add(extendsLinks.linkToFormasPagamento("formas-pagamento"));
		rootEntryPointModel.add(extendsLinks.linkToEstados("estados"));
		rootEntryPointModel.add(extendsLinks.linkToCidades("cidades"));
		rootEntryPointModel.add(extendsLinks.linkToCategoriaProdutos(null, "Categoria"));
		rootEntryPointModel.add(extendsLinks.linkToEstatisticas("estatisticas"));
		
		return rootEntryPointModel;
	}
	
	private static class RootEntryPointModel extends RepresentationModel<RootEntryPointModel> {
	}
	
}
package com.digitalSystems.extendsfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.digitalSystems.extendsfood.api.ExtendsLinks;
import com.digitalSystems.extendsfood.api.controller.RestauranteController;
import com.digitalSystems.extendsfood.api.model.RestauranteModel;
import com.digitalSystems.extendsfood.domain.model.Restaurante;

@Component
public class RestauranteModelAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ExtendsLinks extendsLinks;

	public RestauranteModelAssembler() {
		super(RestauranteController.class, RestauranteModel.class);
	}

	public RestauranteModel toModel(Restaurante restaurante) {

		RestauranteModel restauranteModel = createModelWithId(restaurante.getId(), restaurante);

		modelMapper.map(restaurante, restauranteModel);

		restauranteModel.getCozinha().add(extendsLinks.linkToCidade(restauranteModel.getCozinha().getId()));

		restauranteModel.getEndereco().getCidade()
				.add(extendsLinks.linkToCidade(restauranteModel.getEndereco().getCidade().getId()));

		restauranteModel.add(extendsLinks.linkToRestaurantes("restaurante"));

		restauranteModel
				.add(extendsLinks.linkToRestauranteFormasPagamento(restauranteModel.getId(), "forma-pagamento"));

		restauranteModel.add(extendsLinks.linkToRestauranteResponsaveis(restauranteModel.getId(), "responsaveis"));

		if (restaurante.inativacaoPermitida()) {
			restauranteModel.add(extendsLinks.linkToInativarRestaurante(restauranteModel.getId(), "inativar"));
		}
		if (restaurante.ativacaoPermitida()) {
			restauranteModel.add(extendsLinks.linkToAtivarRestaurante(restauranteModel.getId(), "ativar"));
		}

		if (restaurante.fechamentoPermitido()) {
			restauranteModel.add(extendsLinks.linkToFecharRestaurante(restauranteModel.getId(), "fechar"));
		}
		if (restaurante.aberturaPermitida()) {
			restauranteModel.add(extendsLinks.linkToAbrirRestaurante(restauranteModel.getId(), "Abrir"));
		}

		return restauranteModel;
	}

	public CollectionModel<RestauranteModel> toCollectionModel(Iterable<? extends Restaurante> entities) {
		return super.toCollectionModel(entities).add(extendsLinks.linkToRestaurantes());
	}
}

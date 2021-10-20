package com.digitalSystems.extendsfood.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.digitalSystems.extendsfood.api.ExtendsLinks;
import com.digitalSystems.extendsfood.api.controller.CategoriaProdutoController;
import com.digitalSystems.extendsfood.api.model.CategoriaProdutoModel;
import com.digitalSystems.extendsfood.api.model.CategoriaProdutoResumoModel;
import com.digitalSystems.extendsfood.domain.model.CategoriaProduto;

@Component
public class CategoriaProdutoModelAssembler
		extends RepresentationModelAssemblerSupport<CategoriaProduto, CategoriaProdutoModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ExtendsLinks extendsLinks;

	public CategoriaProdutoModelAssembler() {
		super(CategoriaProdutoController.class, CategoriaProdutoModel.class);
	}

	@Override
	public CategoriaProdutoModel toModel(CategoriaProduto categoriaProduto) {

		CategoriaProdutoModel categoriaProdutoModel = createModelWithId(categoriaProduto.getId(), categoriaProduto,
				categoriaProduto.getRestaurante().getId());

		modelMapper.map(categoriaProduto, categoriaProdutoModel);

		categoriaProdutoModel.add(extendsLinks.linkToCategoriaProdutos(
				categoriaProduto.getRestaurante().getId(), "categorias"));
		
		categoriaProdutoModel.getRestaurante().add(extendsLinks.linkToRestaurante(
				categoriaProduto.getRestaurante().getId()));
		
		categoriaProdutoModel.getProdutos().forEach(produto -> {
			
			produto.add(extendsLinks.linkToProduto(categoriaProduto.getRestaurante().getId(), categoriaProduto.getId(),
					produto.getId()));
		});

		return categoriaProdutoModel;
	}

	private CategoriaProdutoResumoModel toModelResumo(CategoriaProduto categoriaProduto) {

		CategoriaProdutoResumoModel categoriaProdutoModel = modelMapper.map(categoriaProduto,
				CategoriaProdutoResumoModel.class);

		categoriaProdutoModel.add(extendsLinks.linkToCategoriaProdutos(
				categoriaProduto.getRestaurante().getId(), "categorias"));

		return categoriaProdutoModel;
	}

	public List<CategoriaProdutoResumoModel> toCollectionModel(List<CategoriaProduto> categorias) {
		return categorias.stream().map(categoriaProduto -> toModelResumo(categoriaProduto))
				.collect(Collectors.toList());
	}
}

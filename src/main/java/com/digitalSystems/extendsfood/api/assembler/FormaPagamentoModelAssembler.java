package com.digitalSystems.extendsfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.digitalSystems.extendsfood.api.ExtendsLinks;
import com.digitalSystems.extendsfood.api.controller.FormaPagamentoController;
import com.digitalSystems.extendsfood.api.model.FormaPagamentoModel;
import com.digitalSystems.extendsfood.domain.model.FormaPagamento;

@Component
public class FormaPagamentoModelAssembler extends RepresentationModelAssemblerSupport<FormaPagamento, FormaPagamentoModel>{

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ExtendsLinks extendsLinks;
	
	public FormaPagamentoModelAssembler() {
		super(FormaPagamentoController.class, FormaPagamentoModel.class);
	}
	
	public FormaPagamentoModel toModel(FormaPagamento formaPagamento) {
		
		FormaPagamentoModel formaPagamentoModel = createModelWithId(formaPagamento.getId(), formaPagamento);
		
		modelMapper.map(formaPagamento, formaPagamentoModel);
		
		formaPagamentoModel.add(extendsLinks.linkToFormasPagamento("formas-pagamento"));
		
		return formaPagamentoModel;
	}
	
	public CollectionModel<FormaPagamentoModel> toCollectionModel(Iterable<? extends FormaPagamento> entities){
		return super.toCollectionModel(entities)
				.add(extendsLinks.linkToFormasPagamento());
	
	}
}

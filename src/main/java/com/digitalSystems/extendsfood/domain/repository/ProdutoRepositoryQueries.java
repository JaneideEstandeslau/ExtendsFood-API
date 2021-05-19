package com.digitalSystems.extendsfood.domain.repository;

import com.digitalSystems.extendsfood.domain.model.FotoProduto;

public interface ProdutoRepositoryQueries {

	FotoProduto save(FotoProduto foto);
	
	void delete(FotoProduto foto);
	
}
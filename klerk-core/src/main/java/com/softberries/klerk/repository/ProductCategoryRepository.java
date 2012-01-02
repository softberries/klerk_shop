package com.softberries.klerk.repository;

import java.util.Set;

import com.softberries.klerk.domain.ProductCategory;


public interface ProductCategoryRepository {

	void save(ProductCategory pa);

	ProductCategory getById(Long id);

	Set<ProductCategory> fetchAll();
	
	void delete(ProductCategory pa);
}

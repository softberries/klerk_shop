package com.softberries.klerk.repository;

import java.util.Set;

import com.softberries.klerk.domain.ProductAttribute;

public interface ProductAttributeRepository {

	void save(ProductAttribute pa);

	ProductAttribute getById(Long id);

	Set<ProductAttribute> fetchAll();
	
	void delete(ProductAttribute pa);
}

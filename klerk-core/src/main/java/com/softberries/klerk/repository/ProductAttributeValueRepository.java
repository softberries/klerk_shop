package com.softberries.klerk.repository;

import java.util.Set;

import com.softberries.klerk.domain.ProductAttributeValue;

public interface ProductAttributeValueRepository {

	void save(ProductAttributeValue pav);

	ProductAttributeValue getById(Long id);

	Set<ProductAttributeValue> fetchAllForProductAttribute(Long productAttributeId);
	
	void delete(Long id);
}

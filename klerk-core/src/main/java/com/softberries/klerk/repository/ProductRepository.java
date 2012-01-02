package com.softberries.klerk.repository;


import java.util.Set;
import com.softberries.klerk.domain.Product;



public interface ProductRepository {

	void save(Product product);

	Product getById(Long id);

	Set<Product> fetchAll();
	
}

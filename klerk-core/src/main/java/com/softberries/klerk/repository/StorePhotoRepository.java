package com.softberries.klerk.repository;

import java.util.List;

import com.softberries.klerk.domain.CategoryPhoto;
import com.softberries.klerk.domain.ProductPhoto;
import com.softberries.klerk.domain.StorePhoto;

public interface StorePhotoRepository {

	void save(StorePhoto sp);

	StorePhoto getById(Long id);
	
	List<ProductPhoto> findProductPhotos(Long id);
	
	List<CategoryPhoto> findCategoryPhotos(Long id);
	
	void delete(Long id);
}

package com.softberries.klerk.repository;

import com.softberries.klerk.domain.StorePhoto;

public interface StorePhotoRepository {

	void save(StorePhoto sp);

	StorePhoto getById(Long id);
	
	void delete(Long id);
}

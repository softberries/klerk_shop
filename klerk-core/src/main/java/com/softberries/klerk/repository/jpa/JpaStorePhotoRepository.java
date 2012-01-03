package com.softberries.klerk.repository.jpa;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.softberries.klerk.domain.Product;
import com.softberries.klerk.domain.StorePhoto;
import com.softberries.klerk.repository.StorePhotoRepository;

@RequestScoped
public class JpaStorePhotoRepository implements StorePhotoRepository{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void save(StorePhoto sp) {
		if (sp.getId() == null) {
			em.persist(sp);
		} else {
			em.merge(sp);
		}
	}

	@Override
	public StorePhoto getById(Long id) {
		return em.find(StorePhoto.class, id);
	}

	
	@Override
	public void delete(Long id) {
		StorePhoto sp = em.find(StorePhoto.class, id);
		em.remove(sp);
	}

}

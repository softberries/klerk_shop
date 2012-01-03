package com.softberries.klerk.repository.jpa;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.softberries.klerk.domain.CategoryPhoto;
import com.softberries.klerk.domain.Product;
import com.softberries.klerk.domain.ProductCategory;
import com.softberries.klerk.domain.ProductPhoto;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductPhoto> findProductPhotos(Long id) {
		try {
			Product product = em.find(Product.class, id);
			Query query = em.createQuery("SELECT sp FROM StorePhoto sp WHERE sp.product = :product");
			query.setParameter("product", product);
			return (List<ProductPhoto>) query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CategoryPhoto> findCategoryPhotos(Long id) {
		try {
			ProductCategory cat = em.find(ProductCategory.class, id);
			Query query = em.createQuery("SELECT sp FROM StorePhoto sp WHERE sp.category = :category");
			query.setParameter("category", cat);
			return (List<CategoryPhoto>) query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

}

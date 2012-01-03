package com.softberries.klerk.repository.jpa;

import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.softberries.klerk.domain.ProductCategory;
import com.softberries.klerk.repository.ProductCategoryRepository;

@RequestScoped
public class JpaProductCategoryRepository implements ProductCategoryRepository{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void save(ProductCategory pc) {
		if (pc.getId() == null) {
			em.persist(pc);
		} else {
			em.merge(pc);
		}
	}

	@Override
	public ProductCategory getById(Long id) {
		return em.find(ProductCategory.class, id);
	}

	@Override
	public Set<ProductCategory> fetchAll() {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<ProductCategory> query = criteriaBuilder.createQuery(ProductCategory.class);
		Root<ProductCategory> from = query.from(ProductCategory.class);
		CriteriaQuery<ProductCategory> select = query.select(from);

		Set<ProductCategory> result = new HashSet<ProductCategory>();
		result.addAll(em.createQuery(select).getResultList());

		return result;
	}

	@Override
	public void delete(ProductCategory pc) {
		em.remove(pc);
	}

}

package com.softberries.klerk.repository;

import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.softberries.klerk.domain.ProductAttribute;

@RequestScoped
public class JpaProductAttributeRepository implements ProductAttributeRepository{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void save(ProductAttribute pa) {
		if (pa.getId() == null) {
			em.persist(pa);
		} else {
			em.merge(pa);
		}
	}

	@Override
	public ProductAttribute getById(Long id) {
		return em.find(ProductAttribute.class, id);
	}

	@Override
	public Set<ProductAttribute> fetchAll() {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<ProductAttribute> query = criteriaBuilder.createQuery(ProductAttribute.class);
		Root<ProductAttribute> from = query.from(ProductAttribute.class);
		CriteriaQuery<ProductAttribute> select = query.select(from);

		Set<ProductAttribute> result = new HashSet<ProductAttribute>();
		result.addAll(em.createQuery(select).getResultList());

		return result;
	}

	@Override
	public void delete(ProductAttribute pa) {
		em.remove(pa);
	}

}

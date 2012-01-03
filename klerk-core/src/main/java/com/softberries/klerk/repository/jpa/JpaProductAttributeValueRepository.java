package com.softberries.klerk.repository.jpa;

import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.softberries.klerk.domain.ProductAttributeValue;
import com.softberries.klerk.repository.ProductAttributeValueRepository;

@RequestScoped
public class JpaProductAttributeValueRepository implements ProductAttributeValueRepository{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void save(ProductAttributeValue pav) {
		if (pav.getId() == null) {
			em.persist(pav);
		} else {
			em.merge(pav);
		}
	}

	@Override
	public ProductAttributeValue getById(Long id) {
		return em.find(ProductAttributeValue.class, id);
	}

	@Override
	public Set<ProductAttributeValue> fetchAllForProductAttribute(
			Long productAttributeId) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<ProductAttributeValue> query = criteriaBuilder.createQuery(ProductAttributeValue.class);
		Root<ProductAttributeValue> from = query.from(ProductAttributeValue.class);
		CriteriaQuery<ProductAttributeValue> select = query.select(from);

		Set<ProductAttributeValue> result = new HashSet<ProductAttributeValue>();
		result.addAll(em.createQuery(select).getResultList());

		return result;
	}

	@Override
	public void delete(Long id) {
		ProductAttributeValue pav = em.find(ProductAttributeValue.class, id);
		em.remove(pav);
	}

}

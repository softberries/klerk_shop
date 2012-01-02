package com.softberries.klerk.repository;

import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.softberries.klerk.domain.Product;

@RequestScoped
public class JpaProductRepository implements ProductRepository{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void save(Product product) {
		if (product.getId() == null) {
			em.persist(product);
		} else {
			em.merge(product);
		}
	}

	@Override
	public Product getById(Long id) {
		return em.find(Product.class, id);
	}

	@Override
	public Set<Product> fetchAll() {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Product> query = criteriaBuilder.createQuery(Product.class);
		Root<Product> from = query.from(Product.class);
		CriteriaQuery<Product> select = query.select(from);

		Set<Product> result = new HashSet<Product>();
		result.addAll(em.createQuery(select).getResultList());

		return result;
	}

}

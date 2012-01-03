package com.softberries.klerk.repository.jpa;

import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.softberries.klerk.domain.Invoice;
import com.softberries.klerk.repository.InvoiceRepository;

@RequestScoped
public class JpaInvoiceRepository implements InvoiceRepository{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void save(Invoice o) {
		if (o.getId() == null) {
			em.persist(o);
		} else {
			em.merge(o);
		}
	}

	@Override
	public Invoice getById(Long id) {
		return em.find(Invoice.class, id);
	}

	@Override
	public Set<Invoice> fetchAll() {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Invoice> query = criteriaBuilder.createQuery(Invoice.class);
		Root<Invoice> from = query.from(Invoice.class);
		CriteriaQuery<Invoice> select = query.select(from);

		Set<Invoice> result = new HashSet<Invoice>();
		result.addAll(em.createQuery(select).getResultList());

		return result;
	}

	@Override
	public void delete(Long id) {
		Invoice inv = em.find(Invoice.class, id);
		em.remove(inv);
	}

}

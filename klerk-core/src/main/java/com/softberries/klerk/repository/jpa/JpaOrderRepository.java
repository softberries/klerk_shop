package com.softberries.klerk.repository.jpa;

import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.softberries.klerk.domain.Order;
import com.softberries.klerk.domain.OrderStatus;
import com.softberries.klerk.events.OrderStatusChangedEvent;
import com.softberries.klerk.repository.OrderRepository;

@RequestScoped
public class JpaOrderRepository implements OrderRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Inject 
	private Event<OrderStatusChangedEvent> events;
	
	@Override
	public void save(Order o) {
		if (o.getId() == null) {
			em.persist(o);
		} else {
			em.merge(o);
		}
	}

	@Override
	public Order getById(Long id) {
		return em.find(Order.class, id);
	}

	@Override
	public Set<Order> fetchAll() {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Order> query = criteriaBuilder.createQuery(Order.class);
		Root<Order> from = query.from(Order.class);
		CriteriaQuery<Order> select = query.select(from);

		Set<Order> result = new HashSet<Order>();
		result.addAll(em.createQuery(select).getResultList());

		return result;
	}

	@Override
	public Order changeStatus(Long id, OrderStatus status) {
		Order o = em.find(Order.class, id);
		o.setStatus(status);
		//fire events
		events.fire(new OrderStatusChangedEvent(o, status));
		return em.merge(o);
	}

	@Override
	public void delete(Long id) {
		Order o = em.find(Order.class, id);
		em.remove(o);
	}

}

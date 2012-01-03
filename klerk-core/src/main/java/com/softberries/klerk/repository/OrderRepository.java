package com.softberries.klerk.repository;

import java.util.Set;

import com.softberries.klerk.domain.Order;
import com.softberries.klerk.domain.OrderStatus;

public interface OrderRepository {

	void save(Order o);

	Order getById(Long id);

	Set<Order> fetchAll();
	
	Order changeStatus(Long id, OrderStatus status);
	
	void delete(Long id);
}

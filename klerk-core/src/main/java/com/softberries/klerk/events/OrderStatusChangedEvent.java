package com.softberries.klerk.events;

import java.io.Serializable;

import com.softberries.klerk.domain.Order;
import com.softberries.klerk.domain.OrderStatus;

public class OrderStatusChangedEvent implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Order order;
	private OrderStatus newStatus;

	public OrderStatusChangedEvent(Order order, OrderStatus status){
		this.order = order;
		this.newStatus = status;
	}
	
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public OrderStatus getNewStatus() {
		return newStatus;
	}

	public void setNewStatus(OrderStatus newStatus) {
		this.newStatus = newStatus;
	}
	
	
	
}

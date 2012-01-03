package com.softberries.klerk.eventconsumers;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Model;

import com.softberries.klerk.events.OrderStatusChangedEvent;

@Model
public class OrderStatusEventProcessor {

	
	public void orderStatusChanged(@Observes OrderStatusChangedEvent event){
		System.out.println("Order Status changed event fired: " + event);
	}
	
}

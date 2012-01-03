package com.softberries.klerk.repository;


import static org.fest.assertions.Assertions.assertThat;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.softberries.klerk.domain.Order;
import com.softberries.klerk.eventconsumers.OrderStatusEventProcessor;
import com.softberries.klerk.events.OrderStatusChangedEvent;
import com.softberries.klerk.repository.jpa.JpaOrderRepository;

@RunWith(Arquillian.class)
public class OrderRepositoryTest {

	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap.create(JavaArchive.class, "test.jar").addPackage(Order.class.getPackage())
				.addPackage(OrderRepository.class.getPackage())
				.addPackage(JpaOrderRepository.class.getPackage())
				.addPackage(OrderStatusChangedEvent.class.getPackage())
				.addPackage(OrderStatusEventProcessor.class.getPackage())
				.addPackages(true, "org.fest")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml").addAsManifestResource("test-persistence.xml", "persistence.xml");
	}
	
	@Inject
	OrderRepository orderRepository;
	
	@Test
	public void shouldNotBeNull() throws Exception{
		//given
		//when
		//then
		assertThat(orderRepository).isNotNull();
	}
}

package com.softberries.klerk.repository;


import static org.fest.assertions.Assertions.assertThat;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.softberries.klerk.domain.Order;
import com.softberries.klerk.domain.OrderStatus;

@RunWith(Arquillian.class)
public class OrderRepositoryTest {

	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap.create(JavaArchive.class, "test.jar")
				.addPackages(true, "com.softberries.klerk")
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
	@Test
	@UsingDataSet("orders.yml")
	public void shouldFireOrderStatusChangedEvent() throws Exception{
		
		//given
		Order order = new Order();
		order.setStatus(OrderStatus.NEW);
		orderRepository.save(order);
		
		//when
		order = orderRepository.changeStatus(order.getId(), OrderStatus.PENDING);
		
		assertThat(order).isNotNull();
		assertThat(order.getStatus()).isEqualTo(OrderStatus.PENDING);
	}
}

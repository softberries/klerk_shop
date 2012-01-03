package com.softberries.klerk.repository;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.fest.assertions.Assertions.assertThat;
import com.softberries.klerk.domain.Product;
import com.softberries.klerk.repository.jpa.JpaProductRepository;

@RunWith(Arquillian.class)
public class ProductRepositoryTest {

	
	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap.create(JavaArchive.class, "test.jar")
				.addPackages(true, "com.softberries.klerk")
				.addPackages(true, "org.fest")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml").addAsManifestResource("test-persistence.xml", "persistence.xml");
	}

	@Inject
	ProductRepository productRepository;
	
	
	@Test
	public void shouldNotBeNull() throws Exception{
		//given - injected productRepository
		//then
		assertThat(productRepository).isNotNull();
	}
	
}

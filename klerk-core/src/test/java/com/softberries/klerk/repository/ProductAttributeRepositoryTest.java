package com.softberries.klerk.repository;

import static org.fest.assertions.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.ShouldMatchDataSet;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.softberries.klerk.domain.Order;
import com.softberries.klerk.domain.ProductAttribute;
import com.softberries.klerk.domain.ProductAttributeType;
import com.softberries.klerk.domain.ProductAttributeValue;
import com.softberries.klerk.repository.jpa.JpaProductAttributeRepository;


@RunWith(Arquillian.class)
public class ProductAttributeRepositoryTest {

	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap.create(JavaArchive.class, "test.jar")
				.addPackages(true, "com.softberries.klerk")
				.addPackages(true, "org.fest")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml").addAsManifestResource("test-persistence.xml", "persistence.xml");
	}

	@Inject
	ProductAttributeRepository paRepository;
	@Inject
	ProductAttributeValueRepository pavRepository;

	
	@Test
	public void shouldNotBeNull() throws Exception {
		// given
		// paRepository injected
		// when

		// then
		assertThat(paRepository).isNotNull();
	}
	
	@Test
	@ShouldMatchDataSet("product-attributes.yml")
	public void shouldAddAttribute() throws Exception{
		//given
		ProductAttribute pa = new ProductAttribute();
		pa.setType(ProductAttributeType.COMBO_BOX);
		pa.setName("Combo box options");
		pa.setMain(true);
		paRepository.save(pa);
		//assign attribute values
		ProductAttributeValue value = new ProductAttributeValue(pa, "Option One");
		ProductAttributeValue value2 = new ProductAttributeValue(pa, "Option Two");
		
		pavRepository.save(value);
		pavRepository.save(value2);
		
		List<ProductAttributeValue> values = new ArrayList<ProductAttributeValue>();
		values.add(value);
		values.add(value2);
		pa.setValues(values);
		
		paRepository.save(pa);
	}
	
}

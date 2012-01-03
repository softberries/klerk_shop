package com.softberries.klerk.repository;

import static org.fest.assertions.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.ShouldMatchDataSet;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.softberries.klerk.domain.ProductCategory;
import com.softberries.klerk.repository.jpa.JpaProductCategoryRepository;

@RunWith(Arquillian.class)
public class ProductCategoryRepositoryTest {

	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap.create(JavaArchive.class, "test.jar").addPackage(ProductCategory.class.getPackage())
				.addPackage(ProductCategoryRepository.class.getPackage())
				.addPackage(JpaProductCategoryRepository.class.getPackage())
				.addPackages(true, "org.fest")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml").addAsManifestResource("test-persistence.xml", "persistence.xml");
	}

	@Inject
	ProductCategoryRepository categoryRepository;
	
	@Test
	@UsingDataSet("categories.yml")
	public void shouldReturnAllCategories() throws Exception {
		// given
		int expectedAmountOfCategories = 5;

		// when
		Set<ProductCategory> allCategories = categoryRepository.fetchAll();

		// then
		assertThat(allCategories).hasSize(expectedAmountOfCategories);
	}
	@Test
	@UsingDataSet("categories.yml")
	public void shouldReturnTwoMainCategories() throws Exception {
		// given
		int expectedAmountOfCategories = 2;

		// when
		Set<ProductCategory> allCategories = categoryRepository.fetchAll();
		
		Set<ProductCategory> result = new HashSet<ProductCategory>();

		for(ProductCategory pc : allCategories){
			if(pc.isMain()){
				result.add(pc);
			}
		}
		// then
		assertThat(result).hasSize(expectedAmountOfCategories);
	}
	@Test
	@UsingDataSet("categories.yml")
	public void shouldHaveTwoSubCategories() throws Exception {
		// given
		int expectedAmountOfCategories = 2;

		// when
		ProductCategory pc = categoryRepository.getById(new Long(30));

		// then
		assertThat(pc.getSubCategories()).hasSize(expectedAmountOfCategories);
	}
	
	@Test
	@ShouldMatchDataSet("expected-insert-category.yml")
	public void shouldAddCategory() throws Exception{
		//given
		ProductCategory pc = new ProductCategory();
		pc.setMain(true);
		pc.setName("Main Category One");
		
		ProductCategory subPc = new ProductCategory();
		subPc.setName("Subcategory One");
		subPc.setParentCategory(pc);
		
		//when
		categoryRepository.save(pc);
		categoryRepository.save(subPc);
		
		//then
		//should match data set
	}
	
	@Test
	@UsingDataSet("categories.yml")
	@ShouldMatchDataSet("expected-remove-category.yml")
	public void shouldRemoveCategory() throws Exception{
		//given
		//data set
		
		//when
		ProductCategory pc = categoryRepository.getById(new Long(30));
		categoryRepository.delete(pc);
		
		//then
		//should match data set
	}
}

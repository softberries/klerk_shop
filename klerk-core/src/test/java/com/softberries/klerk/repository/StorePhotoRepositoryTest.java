package com.softberries.klerk.repository;

import static org.fest.assertions.Assertions.assertThat;

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

import com.softberries.klerk.domain.Product;
import com.softberries.klerk.domain.ProductPhoto;
import com.softberries.klerk.domain.StorePhoto;
import com.softberries.klerk.repository.jpa.JpaStorePhotoRepository;

@RunWith(Arquillian.class)
public class StorePhotoRepositoryTest {

	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap.create(JavaArchive.class, "test.jar").addPackage(StorePhoto.class.getPackage())
				.addPackage(StorePhotoRepository.class.getPackage())
				.addPackage(JpaStorePhotoRepository.class.getPackage())
				.addPackages(true, "org.fest")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml").addAsManifestResource("test-persistence.xml", "persistence.xml");
	}
	
	@Inject
	StorePhotoRepository storePhotoRepository;
	
	@Inject
	ProductRepository productRepository;
	
	@Test
	public void shouldNotBeNull() throws Exception{
		//given
		//when
		//then
		assertThat(storePhotoRepository).isNotNull();
	}
	
	@Test
	@ShouldMatchDataSet("store-photo.yml")
	public void shouldSaveStorePhotoForProduct() throws Exception{
		//given
		int expectedSizeOfProductPhotos = 1;
		Product p = new Product();
		p.setName("Product One");
		p.setDescription("Product One Description");
		
		productRepository.save(p);
		
		//when
		ProductPhoto pp = new ProductPhoto();
		pp.setFullPhotoUrl("fullPhotoUrl");
		pp.setProduct(p);
		pp.setSmallPhotoUrl("smallPhotoUrl");
		pp.setThumbPhotoUrl("thumbPhotoUrl");
		
		storePhotoRepository.save(pp);
		
		//then
		//should match data set
	}
}

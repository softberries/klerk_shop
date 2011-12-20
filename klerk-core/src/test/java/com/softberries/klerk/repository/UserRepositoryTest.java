package com.softberries.klerk.repository;

import static org.fest.assertions.Assertions.assertThat;

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

import com.softberries.klerk.domain.User;

@RunWith(Arquillian.class)
public class UserRepositoryTest {

	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap.create(JavaArchive.class, "test.jar").addPackage(User.class.getPackage())
				.addPackage(UserRepository.class.getPackage()).addPackages(true, "org.fest")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml").addAsManifestResource("test-persistence.xml", "persistence.xml");
	}

	@Inject
	UserRepository userRepository;

	@Test
	@UsingDataSet("users.yml")
	public void shouldReturnAllUsers() throws Exception {
		// given
		int expectedAmountOfUsers = 5;

		// when
		Set<User> allUsers = userRepository.fetchAll();

		// then
		assertThat(allUsers).hasSize(expectedAmountOfUsers);
	}

	@Test
	@UsingDataSet("users.yml")
	public void shouldReturnUserByItsId() throws Exception {
		// given
		Long userId = 10L;
		String expectedName = "Krzysztof Grajek";

		// when
		User user = userRepository.getById(userId);

		// then
		assertThat(user.getName()).isEqualTo(expectedName);
	}
	@Test
	@ShouldMatchDataSet("expected-insert-user.yml")
	public void shouldSaveNewUser() throws Exception{
		//when
		User u = new User(null, "name","email@email.com", "0002224344", "username", "password");
		userRepository.save(u);
		assertThat(userRepository).isNotNull();
	}
	
	@Test(expected = java.lang.AssertionError.class)
	@ShouldMatchDataSet("expected-insert-user.yml")
	public void shouldThrowAssertionErrorOnEmail() throws Exception{
		//when
		User u = new User(null, "name","some_wrong_email_address", "0002224344", "username", "password");
		userRepository.save(u);
		
		//then exception should be thrown
	}
	
	@Test
	@UsingDataSet("users.yml")
	public void shouldFindByUsernameAndPassword() throws Exception{
		//given
		String username = "kris";
		String password = "kris";
		String expectedName = "Krzysztof Grajek";
		
		//when
		User u = userRepository.findByUsernameAndPassword(username, password);
		
		//then
		assertThat(u).isNotNull();
		assertThat(u.getName()).isEqualTo(expectedName);
		
	}
	@UsingDataSet("users.yml")
	public void shouldThrowNoResultExceptionOnLogin() throws Exception{
		//given
		String username = "asdfasdf";
		String password = "12341234";
		
		//when
		User u = userRepository.findByUsernameAndPassword(username, password);
		
		//then
		assertThat(u).isNull();
	}
	
}

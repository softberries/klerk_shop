package com.softberries.klerk.controllers;

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

import com.softberries.klerk.domain.StoreUser;
import com.softberries.klerk.repository.UserRepository;
import com.softberries.klerk.repository.jpa.JpaUserRepository;
import com.softberries.klerk.security.Secure;
import com.softberries.klerk.security.SecurityInterceptor;
import com.softberries.klerk.util.UniqueStringUtil;

@RunWith(Arquillian.class)
public class UserControllerTest {

	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap.create(JavaArchive.class, "test.jar")
				.addPackages(true, "org.fest")
				.addPackages(true, "com.softberries.klerk")
				.addPackages(true, "org.mindrot.jbcrypt")
				.addAsManifestResource("test-beans.xml", "beans.xml")
				.addAsManifestResource("test-persistence.xml", "persistence.xml");
	}

	@Inject
	private UserController controller;

	@Test
	public void testInjection() throws Exception {
		assertThat(controller).isNotNull();
	}

	@Test
	public void registerUserTest() throws Exception {
		// given
		StoreUser u = new StoreUser(null, "kris", "krzysztof.grajek@googlemail.com", "0601689689", "kris", true);

		// when
		controller.registerUser(u);

		// then
		assertThat(u.getId()).isNotNull();
	}

	@Test
	@UsingDataSet("users.yml")
	public void loginTest() throws Exception {
		// given
		String email = "krzysztof.grajek@googlemail.com";
		String password = "kris";
		// when
		controller.login(email, password);
		// then
		assertThat(controller.getCurrentUser()).isNotNull();
	}

	@Test
	@UsingDataSet("users.yml")
	public void loginNotActivatedUserTest() throws Exception {
		// given
		String email = "tomek.paluch@googlemail.com";
		String password = "kris";
		// when
		controller.login(email, password);
		// then
		assertThat(controller.getCurrentUser()).isNull();
	}

	@Test
	@UsingDataSet("users.yml")
	public void resetPasswordTest() throws Exception {
		// given
		String email = "krzysztof.grajek@googlemail.com";
		String password = "kris";
		// when
		String temp = controller.resetPassword(email);
		// then
		assertThat(temp).isNotNull();
		// and when -- login with old password
		controller.login(email, password);
		// then
		assertThat(controller.getCurrentUser().getResetPasswordCode()).isNotNull();
		assertThat(controller.getCurrentUser().getResetPasswordCode()).isEqualTo(temp);
	}

	@Test
	public void activateUserTest() throws Exception {
		// given
		StoreUser u = new StoreUser(null, "kris", "krzysztof.grajek@googlemail.com", "0601689689", "kris", true);
		// when
		controller.registerUser(u);
		// then
		assertThat(u.getId()).isNotNull();
		// check if user is not activated
		assertThat(u.isActivated()).isFalse();
		// activate user
		controller.activateUser(u.getEmail(), u.getActivationCode());

		// check if activation didn't set current user
		assertThat(controller.getCurrentUser()).isNull();

		// log in to check the activated status
		controller.login("krzysztof.grajek@googlemail.com", "kris");
		assertThat(controller.getCurrentUser()).isNotNull();
		// check if user is activated
		assertThat(controller.getCurrentUser().isActivated()).isTrue();
	}

	@Test
	@UsingDataSet("users.yml")
	public void changePasswordFromResetTest() throws Exception {
		// given
		String email = "krzysztof.grajek@googlemail.com";
		String password = "kris";
		// when
		String temp = controller.resetPassword(email);
		// then
		assertThat(temp).isNotNull();
		// and change password with temp code
		controller.changePasswordFromReset(temp, email, "kriskris");
	}

	@Test
	@UsingDataSet("users.yml")
	public void subscribeNewsletterTest() throws Exception {
		// given
		String email = "krzysztof.grajek@googlemail.com";
		String password = "kris";
		controller.login(email, password);
		assertThat(controller.getCurrentUser()).isNotNull();
		controller.subscribeNewsletter(true);
		assertThat(controller.getCurrentUser().isNewsletterSubscribed()).isTrue();
	}
	
	@Test
	@UsingDataSet("users.yml")
	public void checkUserDataTest() throws Exception{
		//given
		String email_1 = "krzysztof.grajek@googlemail.com"; //should return true
		String email_2 = "kris.g@gmail.com"; //should return false
		
		//when
		boolean test_1 = controller.checkUserExists(new StoreUser(null,"",email_1,"","",false));
		boolean test_2 = controller.checkUserExists(new StoreUser(null,"",email_2,"","",false));
		
		//then
		assertThat(test_1).isTrue();
		assertThat(test_2).isFalse();
	}

}

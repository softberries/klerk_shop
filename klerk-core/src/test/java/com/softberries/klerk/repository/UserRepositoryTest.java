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

import com.softberries.klerk.domain.StoreUser;

@RunWith(Arquillian.class)
public class UserRepositoryTest {

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(JavaArchive.class, "test.jar").addPackages(true, "com.softberries.klerk").addPackages(true, "org.fest").addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml").addAsManifestResource("test-persistence.xml", "persistence.xml");
    }
    @Inject
    UserRepository userRepository;

    @Test
    @UsingDataSet("users.yml")
    public void shouldReturnAllUsers() throws Exception {
        // given
        int expectedAmountOfUsers = 5;

        // when
        Set<StoreUser> allUsers = userRepository.fetchAll();

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
        StoreUser user = userRepository.getById(userId);

        // then
        assertThat(user.getName()).isEqualTo(expectedName);
    }

    @Test
    @ShouldMatchDataSet("expected-insert-user.yml")
    public void shouldSaveNewUser() throws Exception {
        //when
        StoreUser u = new StoreUser(null, "name", "email@email.com", "0002224344", "password", true);
        userRepository.save(u);
        assertThat(userRepository).isNotNull();
    }

    @Test(expected = java.lang.AssertionError.class)
    @ShouldMatchDataSet("expected-insert-user.yml")
    public void shouldThrowAssertionErrorOnEmail() throws Exception {
        //when
        StoreUser u = new StoreUser(null, "name", "some_wrong_email_address", "0002224344", "password", true);
        userRepository.save(u);

        //then exception should be thrown
    }

    @Test
    @UsingDataSet("users.yml")
    public void shouldFindByUsernameAndPassword() throws Exception {
        //given
        String password = "$2a$10$aOn3On97RJYUoxFyZWFlCe2kYvjw53mltSTMUfpEA8QOCFx14hD6S";
        String email = "krzysztof.grajek@googlemail.com";
        String expectedName = "Krzysztof Grajek";

        //when
        StoreUser u = userRepository.findByEmailAndPassword(email, password);

        //then
        assertThat(u).isNotNull();
        assertThat(u.getName()).isEqualTo(expectedName);

    }

    @UsingDataSet("users.yml")
    public void shouldThrowNoResultExceptionOnLogin() throws Exception {
        //given
        String email = "asdfasdf";
        String password = "12341234";

        //when
        StoreUser u = userRepository.findByEmailAndPassword(email, password);

        //then
        assertThat(u).isNull();
    }
}

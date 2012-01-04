package com.softberries.klerk.repository.jpa;

import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.softberries.klerk.domain.User;
import com.softberries.klerk.repository.UserRepository;

@RequestScoped
public class JpaUserRepository implements UserRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void save(User user) {
		if (user.getId() == null) {
			em.persist(user);
		} else {
			em.merge(user);
		}
	}

	@Override
	public User getById(Long id) {
		return em.find(User.class, id);
	}

	@Override
	public Set<User> fetchAll() {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
		Root<User> from = query.from(User.class);
		CriteriaQuery<User> select = query.select(from);

		Set<User> result = new HashSet<User>();
		result.addAll(em.createQuery(select).getResultList());

		return result;
	}

	@Override
	public User findByEmailAndPassword(String email, String password) {
		try {
			Query query = em.createQuery("SELECT u FROM User u WHERE u.email = :email AND u.password = :password");
			query.setParameter("email", email);
			query.setParameter("password", password);
			return (User) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public User findActivatedByEmail(String email) {
		try {
			Query query = em.createQuery("SELECT u FROM User u WHERE u.email = :email AND u.activated = :active");
			query.setParameter("email", email);
			query.setParameter("active", true);
			return (User) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public User findByEmail(String email) {
		try {
			Query query = em.createQuery("SELECT u FROM User u WHERE u.email = :email");
			query.setParameter("email", email);
			return (User) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	

	@Override
	public User findByResetCodeAndEmail(String resetCode, String email) {
		try {
			Query query = em.createQuery("SELECT u FROM User u WHERE u.email = :email AND u.resetPasswordCode = :code");
			query.setParameter("email", email);
			query.setParameter("code", resetCode);
			return (User) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public void activateUser(String email, String activationCode) {
		try {
			Query query = em.createQuery("SELECT u FROM User u WHERE u.email = :email AND u.activationCode = :code");
			query.setParameter("email", email);
			query.setParameter("code", activationCode);
			User u = (User) query.getSingleResult();
			u.setActivated(true);
			em.merge(u);
			em.flush();
		} catch (NoResultException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(User user) {
		em.remove(user);
	}

}

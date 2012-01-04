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

import com.softberries.klerk.domain.StoreUser;
import com.softberries.klerk.repository.UserRepository;

@RequestScoped
public class JpaUserRepository implements UserRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void save(StoreUser user) {
		if (user.getId() == null) {
			em.persist(user);
		} else {
			em.merge(user);
		}
	}

	@Override
	public StoreUser getById(Long id) {
		return em.find(StoreUser.class, id);
	}

	@Override
	public Set<StoreUser> fetchAll() {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<StoreUser> query = criteriaBuilder.createQuery(StoreUser.class);
		Root<StoreUser> from = query.from(StoreUser.class);
		CriteriaQuery<StoreUser> select = query.select(from);

		Set<StoreUser> result = new HashSet<StoreUser>();
		result.addAll(em.createQuery(select).getResultList());

		return result;
	}

	@Override
	public StoreUser findByEmailAndPassword(String email, String password) {
		try {
			Query query = em.createQuery("SELECT u FROM StoreUser u WHERE u.email = :email AND u.password = :password");
			query.setParameter("email", email);
			query.setParameter("password", password);
			return (StoreUser) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public StoreUser findActivatedByEmail(String email) {
		try {
			Query query = em.createQuery("SELECT u FROM StoreUser u WHERE u.email = :email AND u.activated = :active");
			query.setParameter("email", email);
			query.setParameter("active", true);
			return (StoreUser) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public StoreUser findByEmail(String email) {
		try {
			Query query = em.createQuery("SELECT u FROM StoreUser u WHERE u.email = :email");
			query.setParameter("email", email);
			return (StoreUser) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	

	@Override
	public StoreUser findByResetCodeAndEmail(String resetCode, String email) {
		try {
			Query query = em.createQuery("SELECT u FROM StoreUser u WHERE u.email = :email AND u.resetPasswordCode = :code");
			query.setParameter("email", email);
			query.setParameter("code", resetCode);
			return (StoreUser) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public void activateUser(String email, String activationCode) {
		try {
			Query query = em.createQuery("SELECT u FROM StoreUser u WHERE u.email = :email AND u.activationCode = :code");
			query.setParameter("email", email);
			query.setParameter("code", activationCode);
			StoreUser u = (StoreUser) query.getSingleResult();
			u.setActivated(true);
			em.merge(u);
			em.flush();
		} catch (NoResultException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(StoreUser user) {
		em.remove(user);
	}

}

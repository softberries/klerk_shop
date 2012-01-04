package com.softberries.klerk.repository;

import java.util.Set;

import com.softberries.klerk.domain.User;


public interface UserRepository {

	void save(User user);

	User getById(Long id);

	Set<User> fetchAll();
	
	User findByEmailAndPassword(String username, String password);
	
	User findActivatedByEmail(String email);
	
	User findByEmail(String email);
	
	User findByResetCodeAndEmail(String resetCode, String email);
	
	void activateUser(String email, String activationCode);
	
	void delete(User user);
}

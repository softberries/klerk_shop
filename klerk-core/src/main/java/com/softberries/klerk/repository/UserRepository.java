package com.softberries.klerk.repository;

import java.util.Set;

import com.softberries.klerk.domain.User;


public interface UserRepository {

	void save(User user);

	User getById(Long id);

	Set<User> fetchAll();
	
	User findByUsernameAndPassword(String username, String password);
}

package com.softberries.klerk.repository;

import java.util.Set;

import com.softberries.klerk.domain.StoreUser;


public interface UserRepository {

	void save(StoreUser user);

	StoreUser getById(Long id);

	Set<StoreUser> fetchAll();
	
	StoreUser findByEmailAndPassword(String username, String password);
	
	StoreUser findActivatedByEmail(String email);
	
	StoreUser findByEmail(String email);
	
	StoreUser findByResetCodeAndEmail(String resetCode, String email);
	
	void activateUser(String email, String activationCode);
	
	void delete(StoreUser user);
}

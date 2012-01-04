package com.softberries.klerk.controllers;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.SecureRandom;

import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import org.mindrot.jbcrypt.BCrypt;

import com.softberries.klerk.domain.User;
import com.softberries.klerk.exception.AuthenticationException;
import com.softberries.klerk.exception.AuthorizationException;
import com.softberries.klerk.repository.UserRepository;
import com.softberries.klerk.security.LoggedIn;
import com.softberries.klerk.security.Secure;
import com.softberries.klerk.util.UniqueStringUtil;

@SessionScoped
public class UserController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UserRepository userRepository;
	
	@Inject
	private UniqueStringUtil util;
	
	private User currentUser;
	
	
	public void login(String email, String password) throws AuthenticationException{
	    User temp = userRepository.findActivatedByEmail(email);
	    if(temp != null && util.passwordMatches(temp.getPassword(), password)){
	    	currentUser = temp;
	    }else{
	    	currentUser = null;
	    }
	}
	
	public String resetPassword(String email){
		User u = userRepository.findActivatedByEmail(email);
		String randomCode = null;
		if(u != null){
			randomCode = util.generateRandomCode();
			u.setResetPasswordCode(randomCode);
			userRepository.save(u);
		}
		return randomCode;
	}

	public void changePasswordFromReset(String code, String email, String newPassword){
		User u = userRepository.findByResetCodeAndEmail(code, email);
		if(u != null){
			String hashed = util.getHashedPassword(newPassword);
			u.setPassword(hashed);
			u.setResetPasswordCode(null);
			userRepository.save(u);
		}
	}
	public void activateUser(String email, String code){
		userRepository.activateUser(email, code);
	}
	@Secure
	public void updateCurrentUser(){
		userRepository.save(currentUser);
	}
	public void registerUser(User u){
		u.setId(null);
		String plainPass = u.getPassword();
		String hashed = util.getHashedPassword(plainPass);
		u.setPassword(hashed);
		u.setActivationCode(util.generateRandomCode());
		userRepository.save(u);
	}
	@Secure
	public void subscribeNewsletter(boolean subscribe){
		System.out.println("subscribe newsletter...");
		currentUser.setNewsletterSubscribed(subscribe);
	}
	public boolean checkUserExists(User u){
		User user = userRepository.findByEmail(u.getEmail());
		if(user != null){
			//user with that email already exists
			return true;
		}
		return false;
	}
	
	@Produces
	@LoggedIn
	@Named
	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	
	
}

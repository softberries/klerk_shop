package com.softberries.klerk.util;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.SecureRandom;

import javax.enterprise.context.ApplicationScoped;

import org.mindrot.jbcrypt.BCrypt;

@ApplicationScoped
public class UniqueStringUtil implements Serializable{

	/**
	 * Generates random string 
	 * @return Randomly generated string used as temporary login mechanism
	 */
	public String generateRandomCode() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}
	public String getHashedPassword(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}

	public boolean passwordMatches(String dbPass, String plain){
		return BCrypt.checkpw(plain, dbPass);
	}
}
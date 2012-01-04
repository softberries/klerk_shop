package com.softberries.klerk.exception;

/**
 * Thrown by the {@link UserService} methods when the login attempt fails
 * 
 * @author Krzysztof Grajek
 * 
 */
public class AuthenticationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AuthenticationException() {
	}

	public AuthenticationException(String message) {
		super(message);
	}

	public AuthenticationException(Throwable cause) {
		super(cause);
	}

	public AuthenticationException(String message, Throwable cause) {
		super(message, cause);
	}
}

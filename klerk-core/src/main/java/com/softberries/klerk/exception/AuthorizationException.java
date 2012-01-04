package com.softberries.klerk.exception;

/**
 * Thrown by all classes in {@link com.softberries.klerk.service} when the
 * logged in user is not authorized to use the specified resource
 * 
 * @author Krzysztof Grajek
 * 
 */
public class AuthorizationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AuthorizationException() {
	}

	public AuthorizationException(String message) {
		super(message);
	}

	public AuthorizationException(Throwable cause) {
		super(cause);
	}

	public AuthorizationException(String message, Throwable cause) {
		super(message, cause);
	}
}

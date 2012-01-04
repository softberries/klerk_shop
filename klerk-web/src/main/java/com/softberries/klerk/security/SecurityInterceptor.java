package com.softberries.klerk.security;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import com.softberries.klerk.controllers.UserController;
import com.softberries.klerk.domain.User;

@Secure
@Interceptor
public class SecurityInterceptor implements Serializable{

	@Inject
	@LoggedIn
	private User user;
	
	@AroundInvoke 
	public Object checkSecurity(InvocationContext ctx) throws Exception {
		System.out.println("Current user is: " + user);
		return ctx.proceed();
	}
}

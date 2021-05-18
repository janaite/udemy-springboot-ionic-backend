package net.janaite.cursomc.services;

import org.springframework.security.core.context.SecurityContextHolder;

import net.janaite.cursomc.security.UserSS;

public class UserService {

	public static UserSS authenticated() {
		// return user authenticated or null
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}
}

package com.serwis.authentication;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by jakub on 15.03.2018.
 */
public class UserOnline {
	private static String username = null;
	private static String role =null;

	public static String getRole() {
		if (role != null) {
			return role;
		} else throw new UsernameNotFoundException("Nie znaleziono zalogowanego roli zalogowanego użytkownika");
	}

	public static void setRole(String role) {

		UserOnline.role = role;
	}

	public static void setUsername(String username) {
		UserOnline.username = username;
	}

	public static String getUsername() {
		if (username != null) {
			return username;
		} else throw new UsernameNotFoundException("Nie znaleziono zalogowanego użytkownika");
	}
}

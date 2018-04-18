package com.serwis.authentication;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by jakub on 15.03.2018.
 */
public class UserOnline {
	private static String username = null;
	private static int idRole = -1;

	public static int getIdRole() {
		if (idRole != -1) {
			return idRole;
		} else throw new UsernameNotFoundException("Nie znaleziono zalogowanego roli zalogowanego użytkownika");
	}

	public static void setIdRole(int idRole) {

		UserOnline.idRole = idRole;
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

package com.serwis.wrappers;

import com.serwis.entity.UserRole;
import com.serwis.entity.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * Created by jakub on 19.04.2018.
 */
public class UsersWrapper {


	private Integer idUser;
	private String username;
	private String password;
	private String role;

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public ObservableList<UsersWrapper> UsersWrapper(List<Users> users, List<UserRole> userRole) {
		ObservableList<UsersWrapper> usersWrappers = FXCollections.observableArrayList();
		for (int i = 0; i < users.size(); i++) {
			UsersWrapper user= new UsersWrapper();
			user.setIdUser(users.get(i).getIdusers());
			user.setUsername(users.get(i).getUsername());
			user.setPassword(users.get(i).getPassword());
			user.setRole(userRole.get(i).getRole());
			usersWrappers.add(user);
		}
		return usersWrappers;
	}
}

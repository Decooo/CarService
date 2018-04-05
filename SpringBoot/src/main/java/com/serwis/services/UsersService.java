package com.serwis.services;

import com.serwis.entity.Users;
import com.serwis.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jakub on 05.04.2018.
 */
@Service
public class UsersService {

	@Autowired
	private UsersRepository usersRepository;

	public void addUser(Users user) {
		usersRepository.save(user);
	}

	public List<Users> findAll(){
		return usersRepository.findAll();
	}

}

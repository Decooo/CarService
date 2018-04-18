package com.serwis.services;

import com.serwis.entity.UserRole;
import com.serwis.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jakub on 18.04.2018.
 */
@Service
public class UserRoleService {

	@Autowired
	private UserRoleRepository userRoleRepository;

	public List<UserRole> findAll(){
		return userRoleRepository.findAll();
	}

}

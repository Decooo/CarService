package com.serwis.services;

import com.serwis.entity.UserRole;
import com.serwis.entity.Users;
import com.serwis.repository.UserRoleRepository;
import com.serwis.repository.UsersRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by jakub on 09.05.2018.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
@Transactional
@SpringBootTest
public class UsersServiceTest {
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private UserRoleRepository userRoleRepository;

	@Test
	public void addUser() throws Exception {
		List<UserRole> userRoles = userRoleRepository.findAll();
		if (!userRoles.isEmpty()) {
			Users users = new Users();
			users.setUsername("Kosmita");
			users.setPassword("123456");
			users.setId_role(userRoles.get(0).getIdUserRole());
			List<Users> usersList = usersRepository.findAll();
			usersRepository.save(users);
			List<Users> usersList1 = usersRepository.findAll();
			assertEquals(usersList.size()+1,usersList1.size());
			assertEquals(usersList1.get(usersList1.size()-1).getUsername(),"Kosmita");
		}
	}

	@Test
	public void findByUsername() throws Exception {
		List<Users> usersList = usersRepository.findAll();
		if (!usersList.isEmpty()) {
			Users users = usersRepository.findByUsername(usersList.get(0).getUsername());
			assertEquals(users.getUsername(), usersList.get(0).getUsername());
		}
	}

	@Test
	public void deleteInBatch() throws Exception {
		List<Users> deleteUsers = new ArrayList<>();
		List<UserRole> userRoles = userRoleRepository.findAll();
		if (!userRoles.isEmpty()) {
			Users users = new Users();
			users.setUsername("Kosmita");
			users.setPassword("123456");
			users.setId_role(userRoles.get(0).getIdUserRole());
			usersRepository.save(users);
			List<Users> usersList = usersRepository.findAll();
			deleteUsers.add(usersList.get(usersList.size()-1));
			usersRepository.deleteInBatch(deleteUsers);
			List<Users> usersList1 = usersRepository.findAll();
			assertEquals(usersList1.size(),usersList.size()-deleteUsers.size());
		}

	}

	@Test
	public void findByIdUsers() throws Exception {
		List<Users> usersList = usersRepository.findAll();
		if (!usersList.isEmpty()) {
			Users users = usersRepository.findByIdusers(usersList.get(0).getIdusers());
			assertEquals(users.getIdusers(), usersList.get(0).getIdusers());
		}
	}

}
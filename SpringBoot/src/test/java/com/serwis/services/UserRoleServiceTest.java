package com.serwis.services;

import com.serwis.entity.UserRole;
import com.serwis.repository.UserRoleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by jakub on 09.05.2018.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class UserRoleServiceTest {

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Test
	public void findAll() throws Exception {
		List<UserRole> userRole;
		userRole = userRoleRepository.findAll();
		assertEquals(3,userRole.size());
	}

	@Test
	public void getRole() throws Exception {
		String role = userRoleRepository.findById(1).get().getRole();
		assertEquals("Administrator", role);
	}

}
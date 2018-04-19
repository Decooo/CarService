package com.serwis.repository;

import com.serwis.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jakub on 15.03.2018.
 */

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

	Users findByUsername(String username);
	Users findByIdusers(int id);

}


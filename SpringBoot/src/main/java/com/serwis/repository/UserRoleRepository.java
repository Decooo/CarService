package com.serwis.repository;

import com.serwis.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jakub on 18.04.2018.
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
}

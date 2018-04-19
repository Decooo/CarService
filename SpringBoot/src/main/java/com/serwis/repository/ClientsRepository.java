package com.serwis.repository;

import com.serwis.entity.Clients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jakub on 10.04.2018.
 */
@Repository
public interface ClientsRepository extends JpaRepository<Clients, Integer> {
}

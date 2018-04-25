package com.serwis.repository;

import com.serwis.entity.ServiceContracts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jakub on 23.04.2018.
 */
@Repository
public interface ServiceContractsRepository extends JpaRepository<ServiceContracts, Integer> {

}

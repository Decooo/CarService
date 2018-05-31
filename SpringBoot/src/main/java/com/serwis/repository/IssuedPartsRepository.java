package com.serwis.repository;

import com.serwis.entity.IssuedParts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jakub on 31.05.2018.
 */
@Repository
public interface IssuedPartsRepository extends JpaRepository<IssuedParts,Integer>{

	List<IssuedParts> findByStatusIsNot(String status);
	List<IssuedParts> findAllByIdRepairs(int idRepairs);
}

package com.serwis.repository;

import com.serwis.entity.Repairs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jakub on 29.05.2018.
 */
@Repository
public interface RepairsRepository extends JpaRepository<Repairs, Integer>{
	List<Repairs> findByIdCars(int idCars);
	List<Repairs> findByIdClient(int idClient);
	List<Repairs> findByStatusIsNot(String status);
	List<Repairs> findByStatusIs(String status);
}

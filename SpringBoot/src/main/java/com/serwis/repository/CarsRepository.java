package com.serwis.repository;

import com.serwis.entity.Cars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jakub on 05.04.2018.
 */
@Repository
public interface CarsRepository extends JpaRepository<Cars,Integer> {
	Cars findByIdCars(int idCars);
}

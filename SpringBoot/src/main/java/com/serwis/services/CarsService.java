package com.serwis.services;

import com.serwis.entity.Cars;
import com.serwis.repository.CarsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jakub on 05.04.2018.
 */
@Service
public class CarsService {

	@Autowired
	CarsRepository carsRepository;

	public void addCar(Cars car) {
		carsRepository.save(car);
	}

	public List<Cars> findAll() {
		return carsRepository.findAll();
	}

	public void deleteInBatch(List<Cars> cars) {
		carsRepository.deleteInBatch(cars);
	}

	public Cars findByIdCars(int idCars){
		return carsRepository.findByIdCars(idCars);
	}
}

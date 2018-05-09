package com.serwis.services;

import com.serwis.entity.Cars;
import com.serwis.repository.CarsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by jakub on 09.05.2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CarsServiceTest {
	@Autowired
	CarsRepository carsRepository;

	@Test
	public void addCar() throws Exception {
		Cars car = new Cars();
		car.setBrand("Opel");
		car.setModel("Astra");
		car.setVIN("GHYDKJ3495U734054");
		car.setYear_production(2008);
		car.setRegistration_number("RZ5768");
		List<Cars> carsList = carsRepository.findAll();
		carsRepository.save(car);
		List<Cars> carsList2 = carsRepository.findAll();
		assertEquals(carsList.size() + 1, carsList2.size());
		assertEquals(carsList2.get(carsList2.size() - 1).getRegistration_number(), "RZ5768");
	}

	@Test
	public void deleteInBatch() throws Exception {
		List<Cars> carsList = carsRepository.findAll();
		List<Cars> deleteList = new ArrayList<>();
		if (carsList.size() >= 2) {
			deleteList.add(carsList.get(0));
			deleteList.add(carsList.get(carsList.size() - 1));
		} else if (carsList.size() == 1) {
			deleteList.add(carsList.get(0));
		}
		carsRepository.deleteInBatch(deleteList);
		List<Cars> carsList1 = carsRepository.findAll();
		assertEquals(carsList1.size(), carsList.size() - deleteList.size());
	}

}
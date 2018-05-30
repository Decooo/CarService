package com.serwis.services;

import com.serwis.entity.Repairs;
import com.serwis.repository.RepairsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jakub on 29.05.2018.
 */
@Service
public class RepairsService {
	@Autowired
	private RepairsRepository repairsRepository;

	public void save(Repairs repairs) {
		repairsRepository.save(repairs);
	}

	public List<Repairs> findAll() {
		return repairsRepository.findAll();
	}

	public List<Repairs> findByIdCars(int idCars){
		return repairsRepository.findByIdCars(idCars);
	}

	public List<Repairs> findByIdClient(int idClient){
		return repairsRepository.findByIdClient(idClient);
	}
	public List<Repairs> findByStatusIsNot(String status){
		return repairsRepository.findByStatusIsNot(status);
	}

}

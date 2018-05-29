package com.serwis.services;

import com.serwis.entity.Repairs;
import com.serwis.repository.RepairsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

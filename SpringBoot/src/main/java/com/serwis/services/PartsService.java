package com.serwis.services;

import com.serwis.entity.Parts;
import com.serwis.repository.PartsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jakub on 18.05.2018.
 */
@Service
public class PartsService {

	@Autowired
	private PartsRepository partsRepository;

	public void save(Parts part) {
		partsRepository.save(part);
	}

	public List<Parts> findAll() {
		return partsRepository.findAll();
	}

	public Parts findByIdParts(int idParts) {
		return partsRepository.findByIdparts(idParts);
	}

	public void deleteInBatch(List<Parts> parts) {
		partsRepository.deleteInBatch(parts);
	}
}

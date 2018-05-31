package com.serwis.services;

import com.serwis.entity.IssuedParts;
import com.serwis.repository.IssuedPartsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jakub on 31.05.2018.
 */
@Service
public class IssuedPartsService {
	@Autowired
	private IssuedPartsRepository issuedPartsRepository;

	public void save(IssuedParts issuedParts){
		issuedPartsRepository.save(issuedParts);
	}

	public List<IssuedParts> findAll() {
		return issuedPartsRepository.findAll();
	}

	public List<IssuedParts> findByStatusIsNot(String status){
		return issuedPartsRepository.findByStatusIsNot(status);
	}

	public List<IssuedParts> findAllByIdRepairs(int idRepairs){
		return issuedPartsRepository.findAllByIdRepairs(idRepairs);
	}
}

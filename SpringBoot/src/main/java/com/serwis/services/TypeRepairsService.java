package com.serwis.services;

import com.serwis.entity.TypeRepairs;
import com.serwis.repository.TypeRepairsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jakub on 18.05.2018.
 */
@Service
public class TypeRepairsService {

	@Autowired
	private TypeRepairsRepository typeRepairsRepository;

	public List<TypeRepairs> findAll() {
		return typeRepairsRepository.findAll();
	}

	public TypeRepairs findByIdTypeRepairs(int idTypeRepairs){
		return typeRepairsRepository.findByIdTypeRepairs(idTypeRepairs);
	}
}

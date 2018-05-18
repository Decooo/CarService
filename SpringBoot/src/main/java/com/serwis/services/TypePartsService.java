package com.serwis.services;

import com.serwis.entity.TypeParts;
import com.serwis.repository.TypePartsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jakub on 18.05.2018.
 */
@Service
public class TypePartsService {
	@Autowired
	TypePartsRepository typePartsRepository;

	public List<TypeParts> findAll() {
	return typePartsRepository.findAll();
	}
}

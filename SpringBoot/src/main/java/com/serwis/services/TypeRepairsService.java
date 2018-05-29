package com.serwis.services;

import com.serwis.repository.TypeRepairsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jakub on 18.05.2018.
 */
@Service
public class TypeRepairsService {

	@Autowired
	private TypeRepairsRepository typeRepairsRepository;

}

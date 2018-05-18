package com.serwis.services;

import com.serwis.repository.PartsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jakub on 18.05.2018.
 */
@Service
public class PartsService {

	@Autowired
	private PartsRepository partsRepository;
}

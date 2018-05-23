package com.serwis.services;

import com.serwis.repository.PartsOrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jakub on 23.05.2018.
 */
@Service
public class PartsOrdersService {

	@Autowired
	private PartsOrdersRepository partsOrdersRepository;
}

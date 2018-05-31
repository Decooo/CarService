package com.serwis.services;

import com.serwis.repository.IssuedPartsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jakub on 31.05.2018.
 */
@Service
public class IssuedPartsService {
	@Autowired
	private IssuedPartsRepository issuedPartsRepository;
}

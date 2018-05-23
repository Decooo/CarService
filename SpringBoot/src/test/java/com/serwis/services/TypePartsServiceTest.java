package com.serwis.services;

import com.serwis.entity.TypeParts;
import com.serwis.repository.TypePartsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by jakub on 24.05.2018.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
@Transactional
@SpringBootTest
public class TypePartsServiceTest {

	@Autowired
	private TypePartsRepository typePartsRepository;

	@Test
	public void getType() throws Exception {
		List<TypeParts> typePartsList = typePartsRepository.findAll();
		if (!typePartsList.isEmpty()) {
			TypeParts type = typePartsRepository.findByidTypeParts(typePartsList.get(0).getIdTypeParts());
			assertEquals(type.getType(), typePartsList.get(0).getType());
		}
	}

}
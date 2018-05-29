package com.serwis.services;

import com.serwis.entity.Parts;
import com.serwis.entity.TypeParts;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by jakub on 24.05.2018.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
@Transactional
@SpringBootTest
public class PartsServiceTest {
	@Autowired
	private PartsService partsService;
	@Autowired
	private TypePartsService typePartsService;
	@Test
	public void save() throws Exception {
		List<TypeParts> typePartsList = typePartsService.findAll();
		if(!typePartsList.isEmpty()){
			Parts part = new Parts();
			part.setQuantity(3);
			part.setPrice(12.99);
			part.setName("Testowa");
			part.setId_type_parts(typePartsList.get(0).getIdTypeParts());
			List<Parts> partsList1 = partsService.findAll();
			partsService.save(part);
			List<Parts> partsList2 = partsService.findAll();
			assertEquals(partsList1.size()+1,partsList2.size());
			assertEquals(partsList2.get(partsList2.size()-1).getName(),"Testowa");
		}
	}

	@Test
	public void findByIdParts() throws Exception {
		List<Parts> partsList = partsService.findAll();
		if (!partsList.isEmpty()) {
			Parts part = partsService.findByIdParts(partsList.get(0).getIdparts());
			assertEquals(part.getIdparts(), partsList.get(0).getIdparts());
		}
	}

	@Test
	public void deleteInBatch() throws Exception {
		List<TypeParts> typePartsList = typePartsService.findAll();
		if(!typePartsList.isEmpty()){
			Parts part = new Parts();
			part.setQuantity(3);
			part.setPrice(12.99);
			part.setName("Testowa");
			part.setId_type_parts(typePartsList.get(0).getIdTypeParts());
			partsService.save(part);
			List<Parts> partsList1 = partsService.findAll();
			List<Parts> delete = new ArrayList<>();
			delete.add(partsList1.get(partsList1.size()-1));
			partsService.deleteInBatch(delete);
			List<Parts> partsList2 = partsService.findAll();
			assertEquals(partsList1.size()-1,partsList2.size());
		}
	}

}
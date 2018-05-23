package com.serwis.services;

import com.serwis.entity.Orders;
import com.serwis.entity.Parts;
import com.serwis.entity.PartsOrders;
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
public class PartsOrdersServiceTest {
	@Autowired
	private PartsOrdersService partsOrdersService;
	@Autowired
	private PartsService partsService;
	@Autowired
	private OrdersService ordersService;

	@Test
	public void findAllById_OrdersIsNullIsZero() throws Exception {
		List<PartsOrders> partsList = partsOrdersService.findAll();
		if (!partsList.isEmpty()) {
			List<PartsOrders> part = partsOrdersService.findAllById_OrdersIsNullIsZero();
			if(!part.isEmpty()){
				assertEquals(part.get(0).getIdOrders(), 0);
			}
		}
	}

	@Test
	public void findAllById_Orders() throws Exception {
		List<PartsOrders> partsList = partsOrdersService.findAll();
		if (!partsList.isEmpty()) {
			List<PartsOrders> part = partsOrdersService.findAllById_Orders(partsList.get(0).getIdOrders());
			if(!part.isEmpty()){
				assertEquals(part.get(0).getIdOrders(), partsList.get(0).getIdOrders());
			}
		}
	}

	@Test
	public void findByIdParts() throws Exception {
		List<PartsOrders> partsList = partsOrdersService.findAll();
		if (!partsList.isEmpty()) {
			PartsOrders part = partsOrdersService.findByIdParts(partsList.get(0).getIdPartsOrders());
			assertEquals(part.getIdOrders(), partsList.get(0).getIdOrders());
			assertEquals(part.getQuantity(), partsList.get(0).getQuantity());
		}
	}

	@Test
	public void deleteInBatch() throws Exception {
		List<Parts> parts = partsService.findAll();
		List<Orders> orders = ordersService.findAll();
		if(!parts.isEmpty() && !orders.isEmpty()){
			PartsOrders partsOrders = new PartsOrders();
			partsOrders.setIdOrders(orders.get(0).getIdOrders());
			partsOrders.setId_parts(parts.get(0).getIdparts());
			partsOrders.setQuantity(5);
			partsOrdersService.save(partsOrders);
			List<PartsOrders> list1 = partsOrdersService.findAll();
			List<PartsOrders> delete = new ArrayList<>();
			delete.add(list1.get(list1.size()-1));
			partsOrdersService.deleteInBatch(delete);
			List<PartsOrders> list2 = partsOrdersService.findAll();
			assertEquals(list1.size()-1,list2.size());
		}

	}

	@Test
	public void save() throws Exception {
		List<Parts> parts = partsService.findAll();
		List<Orders> orders = ordersService.findAll();
		if(!parts.isEmpty() && !orders.isEmpty()){
			PartsOrders partsOrders = new PartsOrders();
			partsOrders.setIdOrders(orders.get(0).getIdOrders());
			partsOrders.setId_parts(parts.get(0).getIdparts());
			partsOrders.setQuantity(5);
			List<PartsOrders> list1 = partsOrdersService.findAll();
			partsOrdersService.save(partsOrders);
			List<PartsOrders> list2 = partsOrdersService.findAll();
			assertEquals(list1.size()+1,list2.size());
			assertEquals(list2.get(list2.size()-1).getQuantity(),5);
		}
	}

}
package com.serwis.services;

import com.serwis.entity.Orders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by jakub on 24.05.2018.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
@Transactional
@SpringBootTest
public class OrdersServiceTest {

	@Autowired
	private OrdersService ordersService;

	@Test
	public void saveAndFlush() throws Exception {
		Orders order = new Orders();
		order.setValue(1245.45);
		order.setStatus("Zakonczono");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(sdf.format(new Date()));
		order.setDate(date);
		List<Orders> ordersList1 = ordersService.findAll();
		ordersService.saveAndFlush(order);
		List<Orders> ordersList2 = ordersService.findAll();
		assertEquals(ordersList1.size()+1,ordersList2.size());
		assertEquals(ordersList2.get(ordersList2.size()-1).getStatus(),"Zakonczono");
		assertEquals(ordersList2.get(ordersList2.size()-1).getValue(),1245.45,0.1);

	}

}
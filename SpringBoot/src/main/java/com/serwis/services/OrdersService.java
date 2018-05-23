package com.serwis.services;

import com.serwis.entity.Orders;
import com.serwis.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jakub on 23.05.2018.
 */
@Service
public class OrdersService {

	@Autowired
	private OrdersRepository ordersRepository;

	public List<Orders> findAll(){
		return ordersRepository.findAll();
	}
}

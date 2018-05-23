package com.serwis.services;

import com.serwis.entity.PartsOrders;
import com.serwis.repository.PartsOrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jakub on 23.05.2018.
 */
@Service
public class PartsOrdersService {

	@Autowired
	private PartsOrdersRepository partsOrdersRepository;

	public List<PartsOrders> findAllById_OrdersIsNullIsZero() {
		return partsOrdersRepository.findAllByIdOrders(0);
	}

	public List<PartsOrders> findAll() {
		return partsOrdersRepository.findAll();
	}
}

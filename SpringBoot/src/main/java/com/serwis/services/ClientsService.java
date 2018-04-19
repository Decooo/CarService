package com.serwis.services;

import com.serwis.entity.Clients;
import com.serwis.repository.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jakub on 19.04.2018.
 */
@Service
public class ClientsService {

	@Autowired
	private ClientsRepository clientsRepository;

	public void addClient(Clients client) {
		clientsRepository.save(client);
	}

	public List<Clients> findAll() {
		return clientsRepository.findAll();
	}
}

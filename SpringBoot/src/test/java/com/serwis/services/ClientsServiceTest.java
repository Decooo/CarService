package com.serwis.services;

import com.serwis.entity.Clients;
import com.serwis.repository.ClientsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by jakub on 09.05.2018.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class ClientsServiceTest {
	@Autowired
	ClientsRepository clientsRepository;

	@Test
	public void addClient() throws Exception {
		Clients clients = new Clients();
		clients.setName("Iwan");
		clients.setSurname("Grozny");
		long pesel = Long.parseLong("98547536845");
		clients.setPesel(pesel);
		List<Clients> clientsList = clientsRepository.findAll();
		clientsRepository.save(clients);
		List<Clients> clientsList1 = clientsRepository.findAll();
		assertEquals(clientsList.size() + 1, clientsList1.size());
		assertEquals(clientsList1.get(clientsList1.size() - 1).getPesel(), pesel);
	}

	@Test
	public void deleteInBatch() throws Exception {
		List<Clients> deleteClientsList = new ArrayList<>();
		Clients clients = new Clients();
		clients.setName("Iwan");
		clients.setSurname("Grozny");
		long pesel = Long.parseLong("98547536845");
		clients.setPesel(pesel);

		Clients clients2 = new Clients();
		clients2.setName("Ewa");
		clients2.setSurname("Bialic");
		long pesel2 = Long.parseLong("52864537951");
		clients2.setPesel(pesel);

		clientsRepository.save(clients);
		clientsRepository.save(clients2);

		List<Clients> clientsList = clientsRepository.findAll();
		deleteClientsList.add(clientsList.get(clientsList.size() - 1));
		deleteClientsList.add(clientsList.get(clientsList.size() - 2));
		clientsRepository.deleteInBatch(deleteClientsList);
		List<Clients> clientsList1 = clientsRepository.findAll();
		assertEquals(clientsList1.size(), clientsList.size() - deleteClientsList.size());
	}

	@Test
	public void findByIdClients() throws Exception {
		List<Clients> clientsList = clientsRepository.findAll();
		Clients clients = clientsRepository.findByIdClients(clientsList.get(0).getIdClients());
		assertEquals(clients.getPesel(), clientsList.get(0).getPesel());
		assertEquals(clients.getSurname(), clientsList.get(0).getSurname());

	}

}
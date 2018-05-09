package com.serwis.services;

import com.serwis.entity.Clients;
import com.serwis.entity.ServiceContracts;
import com.serwis.repository.ClientsRepository;
import com.serwis.repository.ServiceContractsRepository;
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
public class ServiceContractsServiceTest {
	@Autowired
	private ServiceContractsRepository serviceContractsRepository;
	@Autowired
	private ClientsRepository clientsRepository;

	@Test
	public void add() throws Exception {
		List<Clients> clientsList = clientsRepository.findAll();
		if(!clientsList.isEmpty()){
			ServiceContracts serviceContracts = new ServiceContracts();
			serviceContracts.setIdClient(clientsList.get(0).getIdClients());
			serviceContracts.setWorkingTime(90);
			serviceContracts.setRemainingWorkingTime(45.0);
			serviceContracts.setAmountForParts(5421.0);
			serviceContracts.setRemainingAmountForParts(500);
			List<ServiceContracts> serviceContractsList = serviceContractsRepository.findAll();
			serviceContractsRepository.save(serviceContracts);
			List<ServiceContracts> serviceContractsList1 = serviceContractsRepository.findAll();
			assertEquals(serviceContractsList.size() + 1, serviceContractsList1.size());
			assertEquals(serviceContractsList1.get(serviceContractsList1.size() - 1).getIdClient(), clientsList.get(0).getIdClients());
		}
	}

	@Test
	public void findByIdServiceContracts() throws Exception {
		List<ServiceContracts> serviceContractsList = serviceContractsRepository.findAll();
		ServiceContracts contracts = serviceContractsRepository.findByIdServiceContracts(serviceContractsList.get(0).getIdServiceContracts());
		assertEquals(contracts.getIdClient(), serviceContractsList.get(0).getIdClient());
	}

	@Test
	public void deleteInBatch() throws Exception {
		List<ServiceContracts> deleteContracts = new ArrayList<>();
		List<Clients> clientsList = clientsRepository.findAll();
		if(!clientsList.isEmpty()){
			ServiceContracts serviceContracts = new ServiceContracts();
			serviceContracts.setIdClient(clientsList.get(0).getIdClients());
			serviceContracts.setWorkingTime(90);
			serviceContracts.setRemainingWorkingTime(45.0);
			serviceContracts.setAmountForParts(5421.0);
			serviceContracts.setRemainingAmountForParts(500);
			serviceContractsRepository.save(serviceContracts);
			List<ServiceContracts> serviceContractsList = serviceContractsRepository.findAll();
			deleteContracts.add(serviceContractsList.get(serviceContractsList.size()-1));
			serviceContractsRepository.deleteInBatch(deleteContracts);
			List<ServiceContracts> serviceContractsList1 = serviceContractsRepository.findAll();

			assertEquals(serviceContractsList1.size() , serviceContractsList.size()-deleteContracts.size());
		}
	}

}
package com.serwis.services;

import com.serwis.entity.ServiceContracts;
import com.serwis.repository.ServiceContractsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jakub on 23.04.2018.
 */
@Service
public class ServiceContractsService {

	@Autowired
	ServiceContractsRepository serviceContractsRepository;

	public List<ServiceContracts> findAll() {
		return serviceContractsRepository.findAll();
	}

	public void add(ServiceContracts serviceContracts) {
		serviceContractsRepository.save(serviceContracts);
	}

	public ServiceContracts findByIdServiceContracts(int id){
		return serviceContractsRepository.findByIdServiceContracts(id);
	}

	public void deleteInBatch(List<ServiceContracts> contracts) {
			serviceContractsRepository.deleteInBatch(contracts);
	}
	public ServiceContracts findByIdClient(int id){
		return  serviceContractsRepository.findByIdClient(id);
	}
}

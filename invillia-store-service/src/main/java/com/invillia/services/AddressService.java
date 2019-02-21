package com.invillia.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.invillia.exceptions.EntityNotFoundException;
import com.invillia.exceptions.RequiredFieldException;
import com.invillia.exceptions.StoreException;
import com.invillia.models.Address;
import com.invillia.models.Store;
import com.invillia.repositories.AddressRepository;
import com.invillia.repositories.StoreRepository;

@Service
public class AddressService {

	@Autowired
	private AddressRepository repository;
	@Autowired
	private StoreRepository storeRepository;
	
	private Logger logger = LoggerFactory.getLogger(AddressService.class);

	
	public Address getAddress(Long storeId, Long id) {		
		Address address = repository.getAddressById(storeId, id);
		if (address == null) {
			throw new EntityNotFoundException("There was no address with id: " + String.valueOf(id) + " for storeId: " + String.valueOf(storeId));			
		}
		
		return address;
	}
	
	public List<Address> getAddresses(Long storeId) {
		return repository.getAddressesByStoreId(storeId);
	}
	
	public Address add(Address address, Long storeId) {
		
		validateStore(storeId);
		verifyRequiredField(address);
		
		try {		
			address.setStore(new Store(storeId));			
			return repository.save(address);
		} catch(Exception e) {
			logger.error(e.getMessage());
			throw new StoreException(e.getMessage());
		}
	}
	
	public Address update(Address address, Long storeId, Long id) {
		
		validateStore(storeId);
		validateAddress(id);
		verifyRequiredField(address);
		
		try {			
			address.setId(id);	
			address.setStore(new Store(storeId));
			return repository.save(address);
		} catch(Exception e) {
			logger.error(e.getMessage());
			throw new StoreException(e.getMessage());
		}
	}
	
	public void delete(Long id) {
		try {						
			repository.deleteById(id);
		} catch(EmptyResultDataAccessException e) {			
			throw new EntityNotFoundException("There was no address with the id: " + String.valueOf(id));
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new StoreException(e.getMessage());
		}
	}
	
	private void validateStore(Long storeId) {
		if (storeRepository.getStoreCountById(storeId) <= 0) {
			throw new EntityNotFoundException("There was no store with the id: " + String.valueOf(storeId));
		}
	}
	
	private void validateAddress(Long id) {
		if (repository.getAddressCountById(id) <= 0) {
			throw new EntityNotFoundException("There was no address with the id: " + String.valueOf(id));
		}
	}
	
	private void verifyRequiredField(Address address) {
		if (address.getAddress() == null || address.getAddress().equals("")) {
			throw new RequiredFieldException("The field address must have a value");
		}
	}
}
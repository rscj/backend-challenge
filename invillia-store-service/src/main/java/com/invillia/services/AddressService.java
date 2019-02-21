package com.invillia.services;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invillia.exceptions.RequiredFieldException;
import com.invillia.exceptions.StoreException;
import com.invillia.exceptions.StoreNotFoundException;
import com.invillia.models.Address;
import com.invillia.models.Store;
import com.invillia.repositories.AddressRepository;

@Service
public class AddressService {

	@Autowired
	private AddressRepository repository;
	
	private Logger logger = LoggerFactory.getLogger(AddressService.class);

	
	public Address getAddress(Long storeId, Long id) {		
		Address address = repository.getAddressById(storeId, id);
		if (address == null) {
			throw new StoreNotFoundException("There was no address with id: " + String.valueOf(id) + " for storeId: " + String.valueOf(storeId));			
		}
		
		return address;
	}
	
	public List<Address> getAddresses(Long storeId) {
		return repository.getAddressesByStoreId(storeId);
	}
	
	public Address add(Address address, Long storeId) {
		verifyRequiredField(address);
		
		try {		
			address.setStore(new Store(storeId));
			address.setCreatedDate(new Date());
			return repository.save(address);
		} catch(Exception e) {
			logger.error(e.getMessage());
			throw new StoreException(e.getMessage());
		}
	}
	
	public Address update(Address address, Long id) {
		try {			
			address.setId(id);			
			address.setUpdatedDate(new Date());
			return repository.save(address);
		} catch(Exception e) {
			logger.error(e.getMessage());
			throw new StoreException(e.getMessage());
		}
	}
	
	public void delete(Long id) throws EntityNotFoundException, StoreException {
		try {			
			Address address = repository.findById(id).get();
			address.setDeleted(true);
			address.setDeletedDate(new Date());
			repository.save(address);
		} catch(NoSuchElementException e) {			
			throw new StoreNotFoundException("There was no address with the id: " + String.valueOf(id));
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new StoreException(e.getMessage());
		}
	}
	
	private void verifyRequiredField(Address address) {
		if (address.getAddress() == null || address.getAddress().equals("")) {
			throw new RequiredFieldException("The field address must have a value");
		}
	}
}
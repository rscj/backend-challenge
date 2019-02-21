package com.invillia.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.invillia.exceptions.DuplicatedNameException;
import com.invillia.exceptions.RequiredFieldException;
import com.invillia.exceptions.StoreException;
import com.invillia.exceptions.EntityNotFoundException;
import com.invillia.models.Store;
import com.invillia.repositories.StoreRepository;

@Service
public class StoreService {

	@Autowired
	private StoreRepository repository;
	
	private Logger logger = LoggerFactory.getLogger(StoreService.class);

	
	public Store getStore(Long id) {		
		Store store = repository.getStoreById(id);
		if (store == null) {
			throw new EntityNotFoundException("There was no store with id: " + String.valueOf(id));				
		}
		return store;		
	}
	
	public List<Store> getStores() {
		return repository.findAll();
	}
	
	public Store add(Store store) {

		verifyRequiredField(store);
		
		if (repository.getCountStoreByName(store.getName()) > 0) {
			throw new DuplicatedNameException("Its already has an store with this name");
		}
		
		try {					
			return repository.save(store);
		} catch(Exception e) {
			logger.error(e.getMessage());
			throw new StoreException(e.getMessage());
		}
	}
	
	public Store update(Store store, Long id) {
		
		verifyRequiredField(store);
		
		if (repository.getCountOtherStoresByName(store.getName(), store.getId()) > 0) {
			throw new DuplicatedNameException("Its already has an store with this name");
		}
		
		try {			
			store.setId(id);			
			return repository.save(store);
		} catch(Exception e) {
			logger.error(e.getMessage());
			throw new StoreException(e.getMessage());
		}
	}
	
	public void delete(Long id) {		
		try {		
			//TODO - Make a call to invillia-order-service to be sure that this order does not has any order
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new StoreException(e.getMessage());
		}
	}
	
	private void verifyRequiredField(Store store) {
		if (store.getName() == null || store.getName().equals("")) {
			throw new RequiredFieldException("The field name must have a value");
		}
	}
}
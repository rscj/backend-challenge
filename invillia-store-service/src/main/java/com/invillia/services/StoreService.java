package com.invillia.services;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invillia.exceptions.DuplicatedNameException;
import com.invillia.exceptions.RequiredFieldException;
import com.invillia.exceptions.StoreException;
import com.invillia.exceptions.StoreNotFoundException;
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
			throw new StoreNotFoundException("There was no store with id: " + String.valueOf(id));				
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
			store.setCreatedDate(new Date());
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
			store.setUpdatedDate(new Date());
			return repository.save(store);
		} catch(Exception e) {
			logger.error(e.getMessage());
			throw new StoreException(e.getMessage());
		}
	}
	
	public void delete(Long id) {		
		try {			
			Store store = repository.findById(id).get();
			store.setDeleted(true);
			store.setDeletedDate(new Date());
			repository.save(store);
		} catch(NoSuchElementException e) {			
			throw new StoreNotFoundException("There was no store with the id: " + String.valueOf(id));
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
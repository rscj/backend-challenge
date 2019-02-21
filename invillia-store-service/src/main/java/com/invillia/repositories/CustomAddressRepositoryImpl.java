package com.invillia.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.invillia.models.Address;

public class CustomAddressRepositoryImpl implements CustomAddressRepository {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public int getAddressCountById(Long id) {
		TypedQuery<Long> query = manager.createNamedQuery("Address.getAddressCountById", Long.class);
		query.setParameter("id", id);
		return query.getSingleResult().intValue();
	}
	
	@Override
	public Address getAddressById(Long storeId, Long id) {
		try {
			TypedQuery<Address> query = manager.createNamedQuery("Address.findById", Address.class);
			query.setParameter("storeId", storeId);
			query.setParameter("id", id);
			return query.getSingleResult();
		} catch(NoResultException e) {
			return null;
		}
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Address> getAddressesByStoreId(Long storeId) {		
		TypedQuery<Address> query = manager.createNamedQuery("Address.findAllByStoreId", Address.class);
		query.setParameter("storeId", storeId);
		return query.getResultList();
	}
}
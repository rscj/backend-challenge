package com.invillia.repositories;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.invillia.models.Store;

public class CustomStoreRepositoryImpl implements CustomStoreRepository {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public int getStoreCountById(Long id) {
		TypedQuery<Long> query = manager.createNamedQuery("Store.getStoreCountById", Long.class);
		query.setParameter("id", id);
		return query.getSingleResult().intValue();
	}
	
	@Override
	public int getCountStoreByName(String name) {
		Query query = manager.createNamedQuery("Store.getCountStoreByName");
		query.setParameter("name", name);
		return ((Long) query.getSingleResult()).intValue();
	}

	@Override
	public int getCountOtherStoresByName(String name, Long id) {
		Query query = manager.createNamedQuery("Store.getCountOtherStoresByName");
		query.setParameter("name", name);
		query.setParameter("id", id);
		return ((Long) query.getSingleResult()).intValue();
	}

	@Override	
	public Store getStoreById(Long id) {
		try {
			TypedQuery<Store> query = manager.createNamedQuery("Store.getStoreById", Store.class);
			query.setParameter("id", id);
			return query.getSingleResult();
		} catch(NoResultException e) {
			return null;
		}
	}
}
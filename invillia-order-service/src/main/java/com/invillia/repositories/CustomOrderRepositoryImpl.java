package com.invillia.repositories;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.invillia.models.Order;;

public class CustomOrderRepositoryImpl implements CustomOrderRepository {

	@PersistenceContext
	private EntityManager manager;
		
	@Override	
	public Order getOrderById(Long id) {
		try {
			TypedQuery<Order> query = manager.createNamedQuery("Store.getOrderById", Order.class);
			query.setParameter("id", id);
			return query.getSingleResult();
		} catch(NoResultException e) {
			return null;
		}
	}
}
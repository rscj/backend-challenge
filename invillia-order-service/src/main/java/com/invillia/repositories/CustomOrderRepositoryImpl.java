package com.invillia.repositories;

import java.util.List;

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
			TypedQuery<Order> query = manager.createNamedQuery("Order.getOrderById", Order.class);
			query.setParameter("id", id);
			return query.getSingleResult();
		} catch(NoResultException e) {
			return null;
		}
	}

	@Override
	public int isOrderExists(Long id) {
		TypedQuery<Long> query = manager.createNamedQuery("Order.getCountOrderById", Long.class);
		query.setParameter("id", id);
		return query.getSingleResult().intValue();
	}

	@Override
	public List<Order> getOrdersByStoreId(Long storeId) {
		TypedQuery<Order> query = manager.createNamedQuery("Order.getOrdersByStoreId", Order.class);
		query.setParameter("storeId", storeId);
		return query.getResultList();
	}
}
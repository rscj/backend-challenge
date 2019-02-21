package com.invillia.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.invillia.models.OrderItem;;

public class CustomOrderItemRepositoryImpl implements CustomOrderItemRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<OrderItem> getItemsByOrderId(Long orderId) {
		TypedQuery<OrderItem> query = manager.createNamedQuery("Order.getItemsByOrderId", OrderItem.class);
		query.setParameter("orderId", orderId);
		return query.getResultList();
	}
}
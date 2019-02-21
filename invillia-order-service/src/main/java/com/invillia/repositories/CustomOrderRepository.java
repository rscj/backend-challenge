package com.invillia.repositories;

import java.util.List;

import com.invillia.models.Order;

public interface CustomOrderRepository {
	
	public Order getOrderById(Long id);
	public int isOrderExists(Long id);
	public List<Order> getOrdersByStoreId(Long storeId);
}
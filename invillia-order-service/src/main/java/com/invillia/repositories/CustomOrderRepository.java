package com.invillia.repositories;

import com.invillia.models.Order;

public interface CustomOrderRepository {
	
	public Order getOrderById(Long id);
}
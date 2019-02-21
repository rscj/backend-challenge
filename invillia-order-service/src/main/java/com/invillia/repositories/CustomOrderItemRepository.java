package com.invillia.repositories;

import java.util.List;

import com.invillia.models.OrderItem;

public interface CustomOrderItemRepository {
	
	public List<OrderItem> getItemsByOrderId(Long orderId);
}
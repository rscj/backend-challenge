package com.invillia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invillia.models.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>, CustomOrderItemRepository{

}
package com.invillia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invillia.models.Order;

public interface OrderRepository extends JpaRepository<Order, Long>, CustomOrderRepository {

}
package com.invillia.services;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.invillia.exceptions.OrderException;
import com.invillia.exceptions.RequiredFieldException;
import com.invillia.exceptions.EntityNotFoundException;
import com.invillia.exceptions.InvalidValueException;
import com.invillia.models.Order;
import com.invillia.models.OrderItem;
import com.invillia.repositories.OrderItemRepository;
import com.invillia.repositories.OrderRepository;

@Service
public class OrderItemService {

	@Autowired
	private OrderItemRepository repository;
	@Autowired
	private OrderRepository orderRepository;
	
	private Logger logger = LoggerFactory.getLogger(OrderItemService.class);

	public List<OrderItem> getOrderItems(Long orderId) {
		return repository.getItemsByOrderId(orderId);
	}
	
	public OrderItem add(OrderItem item, Long orderId) {

		verifyOrderId(orderId);
		validateRequiredDescription(item);
		validateUnitPrice(item);
		validateQuantity(item);
			
		try {					
			item.setOrder(new Order(orderId));
			return repository.save(item);
		} catch(Exception e) {
			logger.error(e.getMessage());
			throw new OrderException(e.getMessage());
		}
	}
	
	public OrderItem update(OrderItem item, Long orderId, Long id) {
				
		verifyOrderId(orderId);
		validateRequiredDescription(item);
		validateUnitPrice(item);
		validateQuantity(item);
				
		try {			
			item.setOrder(new Order(orderId));
			item.setId(id);
			return repository.save(item);
		} catch(Exception e) {
			logger.error(e.getMessage());
			throw new OrderException(e.getMessage());
		}
	}
		
	public void delete(Long id) {		
		try {			
			repository.deleteById(id);
		} catch(EmptyResultDataAccessException e) {			
			throw new EntityNotFoundException("There was no order item with the id: " + String.valueOf(id));
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new OrderException(e.getMessage());
		}
	}
	
	private void validateRequiredDescription(OrderItem item) {
		if (item.getDescription() == null || item.getDescription().equals("")) {
			throw new RequiredFieldException("The item description is required");
		}
	}
	
	private void validateUnitPrice(OrderItem item) {
		if (item.getUnitPrice().compareTo(new BigDecimal(0)) <= 0) {
			throw new InvalidValueException("The value for field unit price must be greater than 0");			
		}
	}
	
	private void validateQuantity(OrderItem item) {
		if (item.getQuantity() <= 0) {
			throw new InvalidValueException("The value for field quantity must be greater than 0");
		}
	}
	
	private void verifyOrderId(Long orderId) {
		if (orderRepository.isOrderExists(orderId) == 0) {
			throw new EntityNotFoundException("The order with id: " + String.valueOf(orderId) + " does not exists");
		}
	}
}
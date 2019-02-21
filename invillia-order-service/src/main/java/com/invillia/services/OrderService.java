package com.invillia.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invillia.exceptions.OrderException;
import com.invillia.exceptions.EntityNotFoundException;
import com.invillia.models.Order;
import com.invillia.repositories.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;
	
	private Logger logger = LoggerFactory.getLogger(OrderService.class);

	
	public Order getOrder(Long id) {		
		Order order = repository.getOrderById(id);
		if (order == null) {
			throw new EntityNotFoundException("There was no store with id: " + String.valueOf(id));				
		}
		return order;		
	}
	
	public List<Order> getOrders(Long storeId) {
		return repository.getOrdersByStoreId(storeId);
	}
	
	public Order add(Order order, Long storeId) {

		try {					
			order.setStore(storeId);
			order.setStatus("NEW");
			return repository.save(order);
		} catch(Exception e) {
			logger.error(e.getMessage());
			throw new OrderException(e.getMessage());
		}
	}
	
	public Order update(Order order, Long id) {
		
		//Validar unit price e quantity
		//verifyRequiredField(store);
				
		try {			
			order.setId(id);
			return repository.save(order);
		} catch(Exception e) {
			logger.error(e.getMessage());
			throw new OrderException(e.getMessage());
		}
	}
	
	public void delete(Long id) {		
		try {			
			repository.deleteById(id);
		} catch(NoSuchElementException e) {			
			throw new EntityNotFoundException("There was no order with the id: " + String.valueOf(id));
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new OrderException(e.getMessage());
		}
	}
	
	/*private void verifyRequiredField(Store store) {
		if (store.getName() == null || store.getName().equals("")) {
			throw new RequiredFieldException("The field name must have a value");
		}
	}*/
}
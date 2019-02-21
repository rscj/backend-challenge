package com.invillia.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invillia.exceptions.OrderException;
import com.invillia.exceptions.OrderNotFoundException;
import com.invillia.models.Order;
import com.invillia.models.OrderItem;
import com.invillia.repositories.OrderItemRepository;
import com.invillia.repositories.OrderRepository;

@Service
public class OrderItemService {

	@Autowired
	private OrderItemRepository repository;
	
	private Logger logger = LoggerFactory.getLogger(OrderItemService.class);

	public List<OrderItem> getOrderItems() {
		return repository.findAll();
	}
	
	public OrderItem add(OrderItem item) {

		//Validar unit price e quantity
		//verifyRequiredField(order);
			
		try {					
			return repository.save(item);
		} catch(Exception e) {
			logger.error(e.getMessage());
			throw new OrderException(e.getMessage());
		}
	}
		
	public void delete(Long id) {		
		try {			
			repository.deleteById(id);
		} catch(NoSuchElementException e) {			
			throw new OrderNotFoundException("There was no order item with the id: " + String.valueOf(id));
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
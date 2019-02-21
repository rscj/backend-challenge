package com.invillia.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.invillia.models.Order;
import com.invillia.services.OrderService;

@RestController
@RequestMapping("/invillia/order")
public class OrderResource {

	@Autowired
	private OrderService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<Order> getOrder(@PathVariable("id") final Long id) {
		
		Order order = service.getOrder(id);			
		return ResponseEntity.ok(order);
	}
	
	@GetMapping
	public ResponseEntity<List<Order>> getOrders() {
		
		List<Order> orders = service.getOrders(); 
		return ResponseEntity.ok(orders);		
	}
	
	@PostMapping
	public ResponseEntity<Object> addOrder(@RequestBody Order order, UriComponentsBuilder builder) {
					
		service.add(order);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(order.getId()).toUri();
		return ResponseEntity.created(location).body(order);		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Order> updateOrder(@RequestBody Order order, @PathVariable("id") final Long id) {

		service.update(order, id);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteOrder(@PathVariable("id") final Long id) {

		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
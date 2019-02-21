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

import com.invillia.models.OrderItem;
import com.invillia.services.OrderItemService;

@RestController
@RequestMapping("/invillia/order/{orderId}/item")
public class OrderItemResource {

	@Autowired
	private OrderItemService service;
	
	@GetMapping
	public ResponseEntity<List<OrderItem>> getOrderItems(@PathVariable("orderId") final long orderId) {
		
		List<OrderItem> items = service.getOrderItems(orderId);
		return ResponseEntity.ok(items);		
	}
	
	@PostMapping
	public ResponseEntity<Object> addOrderItem(@RequestBody OrderItem item, 
											   @PathVariable("orderId") final long orderId,
											   UriComponentsBuilder builder) {
					
		service.add(item, orderId);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(item.getId()).toUri();
		return ResponseEntity.created(location).body(item);		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateOrderItem(@RequestBody OrderItem item,
			                                      @PathVariable("orderId") final long orderId,
			                                      @PathVariable("id") final long id,
			                                      UriComponentsBuilder builder) {
					
		service.update(item, orderId, id);
		return ResponseEntity.noContent().build();			
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteOrderItem(@PathVariable("id") final Long id) {

		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
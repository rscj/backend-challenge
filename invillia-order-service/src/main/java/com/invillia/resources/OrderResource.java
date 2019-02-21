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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.invillia.exceptions.EntityNotFoundException;
import com.invillia.exceptions.OrderException;
import com.invillia.models.Order;
import com.invillia.models.Store;
import com.invillia.services.OrderService;

@RestController
@RequestMapping("/invillia")
public class OrderResource {

	@Autowired
	private OrderService service;
	@Autowired
	private RestTemplate restTemplate;
		
	@GetMapping("/order/{id}")
	public ResponseEntity<Order> getOrder(@PathVariable("id") final Long id) {
		
		Order order = service.getOrder(id);			
		return ResponseEntity.ok(order);
	}
	
	@GetMapping("/store/{storeId}/order")
	public ResponseEntity<List<Order>> getOrders(@PathVariable("storeId") Long storeId) {
		
		storeId = getStoreById(storeId);
		
		List<Order> orders = service.getOrders(storeId); 
		return ResponseEntity.ok(orders);		
	}
	
	@PostMapping("/store/{storeId}/order")
	public ResponseEntity<Object> addOrder(@RequestBody Order order,
										   @PathVariable("storeId") long storeId,
										   UriComponentsBuilder builder) {
				
		storeId = getStoreById(storeId);
		
		service.add(order, storeId);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(order.getId()).toUri();
		return ResponseEntity.created(location).body(order);
	}
	
	@PutMapping("/order/{id}")
	public ResponseEntity<Order> updateOrder(@RequestBody Order order,											
											 @PathVariable("id") final Long id) {

		service.update(order, id);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/order/{id}")
	public ResponseEntity<Void> deleteOrder(@PathVariable("id") final Long id) {

		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	private Long getStoreById(Long storeId) {
		try {
			ResponseEntity<Store> store = restTemplate.getForEntity("http://localhost:8762/invillia-store-service/invillia/store/" + String.valueOf(storeId), Store.class);		
			return store.getBody().getId();
		} catch(Exception e) {
			if (e.getMessage().contains("404")) {
				throw new EntityNotFoundException("There was no store with this id: " + String.valueOf(storeId));
			}
			
			throw new OrderException("There was an error");
		}			
	}
}
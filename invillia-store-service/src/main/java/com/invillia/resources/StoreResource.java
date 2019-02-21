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

import com.invillia.models.Store;
import com.invillia.services.StoreService;

@RestController
@RequestMapping("/invillia/store")
public class StoreResource {

	@Autowired
	private StoreService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<Store> getStore(@PathVariable("id") final Long id) {
		
		Store store = service.getStore(id);			
		return ResponseEntity.ok(store);
	}
	
	@GetMapping
	public ResponseEntity<List<Store>> getStores() {
		
		List<Store> stores = service.getStores(); 
		return ResponseEntity.ok(stores);		
	}
	
	@PostMapping
	public ResponseEntity<Object> add(@RequestBody Store store, UriComponentsBuilder builder) {
					
		service.add(store);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(store.getId()).toUri();
		return ResponseEntity.created(location).body(store);		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Store> update(@RequestBody Store store, @PathVariable("id") final Long id) {

		service.update(store, id);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") final Long id) {

		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}

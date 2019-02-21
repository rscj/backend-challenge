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

import com.invillia.models.Address;
import com.invillia.services.AddressService;

@RestController
@RequestMapping("/invillia/store/{storeId}/address")
public class AddressResource {

	@Autowired
	private AddressService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<Address> getAddress(@PathVariable("storeId") final Long storeId, 
			                                  @PathVariable("id") final Long id) {
		Address address = service.getAddress(storeId, id);
		return ResponseEntity.ok(address);
	}
	
	@GetMapping
	public ResponseEntity<List<Address>> getAddresses(@PathVariable("storeId") final Long storeId) {
		
		List<Address> address = service.getAddresses(storeId); 
		return ResponseEntity.ok(address);
	}
	
	@PostMapping
	public ResponseEntity<Address> addAddress(@RequestBody Address address, @PathVariable("storeId") final Long storeId, UriComponentsBuilder builder) {		
		
		service.add(address, storeId);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(address.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Address> updateAddress(@RequestBody Address address,
			                              		 @PathVariable("id") final Long id,
			                              		 @PathVariable("storeId") final Long storeId,
			                              		 UriComponentsBuilder builder) {		
		
		service.update(address, storeId, id);		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAddress(@PathVariable("id") final Long id) {
		
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}

package com.invillia.repositories;

import java.util.List;

import com.invillia.models.Address;

public interface CustomAddressRepository {

	public int getAddressCountById(Long id);
	public Address getAddressById(Long storeId, Long id);
	public List<Address> getAddressesByStoreId(Long storeId);
}
package com.invillia.repositories;

import com.invillia.models.Store;

public interface CustomStoreRepository {
	
	public int getCountStoreByName(String name);
	public int getCountOtherStoresByName(String name, Long id);
	public Store getStoreById(Long id);
}
package com.invillia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invillia.models.Store;


public interface StoreRepository extends JpaRepository<Store, Long>, CustomStoreRepository {
	
	public Store findByName(String name);
}
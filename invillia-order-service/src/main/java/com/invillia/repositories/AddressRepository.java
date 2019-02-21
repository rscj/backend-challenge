package com.invillia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invillia.models.Address;

public interface AddressRepository extends JpaRepository<Address, Long>, CustomAddressRepository {

}
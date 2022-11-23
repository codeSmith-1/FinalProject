package com.skilldistillery.childcare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.childcare.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
	Address queryById(int id);

	Address findByUsername(String username);

}

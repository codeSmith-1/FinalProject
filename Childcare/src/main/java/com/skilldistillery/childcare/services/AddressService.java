package com.skilldistillery.childcare.services;

import java.util.List;



import com.skilldistillery.childcare.entities.Address;



public interface AddressService {
	

	
	List<Address> listAllAddresses(String username);

	Address showAddressById(int id);

	Address create(Address address);

	Address update(int addressId, Address address);

	boolean delete(int addressId);

}

package com.skilldistillery.childcare.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.childcare.entities.Address;
import com.skilldistillery.childcare.repositories.AddressRepository;

@Service
public abstract class AddressServiceImpl implements AddressService {

	@Autowired
	public AddressRepository adrRepo;
	
	
	

	@Override
	public Address showAddressById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Address create(Address address) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Address update(int addressId, Address address) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(int addressId) {
		// TODO Auto-generated method stub
		return false;
	}

}

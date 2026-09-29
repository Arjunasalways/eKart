package com.arjun.ekart.addresses.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arjun.ekart.addresses.dto.AddressCreationRequest;
import com.arjun.ekart.addresses.dto.AddressResponse;
import com.arjun.ekart.addresses.dto.AddressUpdateRequest;
import com.arjun.ekart.addresses.entity.Address;
import com.arjun.ekart.addresses.mapper.AddressMapper;
import com.arjun.ekart.addresses.repository.AddressRepository;
import com.arjun.ekart.users.entity.User;
import com.arjun.ekart.users.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class AddressService {
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private AddressMapper addressMapper;
	
	@Autowired
	private UserRepository userRepository;

	public AddressResponse addAddress(String email, AddressCreationRequest request) {
		// TODO Auto-generated method stub
		
		Address address=addressMapper.toEntity(request);
		
		User user=userRepository.findByEmail(email)
				.orElseThrow(()-> new RuntimeException("user not found"));
		
		address.setUser(user);
		addressRepository.save(address);
		
		return addressMapper.toResponse(address);
	}

	public List<AddressResponse> getAllAddresses(String email) {
		
		User user=userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not found"));
		
		// TODO Auto-generated method stub
		
		return addressRepository.findByUser(user)
				.stream()
				.map(addressMapper::toResponse)
				.toList();
	}

	public AddressResponse getAddress(String email,Long addressId) {
	
		// TODO Auto-generated method stub
		User user = userRepository.findByEmail(email)
		        .orElseThrow(()->new RuntimeException("user not found"));

		Address address = addressRepository.findById(addressId)
		        .orElseThrow(()->new RuntimeException("addres not found"));

		if (!address.getUser().getId().equals(user.getId())) {
		    throw new RuntimeException("Access denied");
		}
		
		return addressMapper.toResponse(address);
	}

	@Transactional
	public String deleteAllAddresses(String email) {
		// TODO Auto-generated method stub
		User user=userRepository.findByEmail(email).
				orElseThrow(() -> new RuntimeException("User not found"));
		
		addressRepository.deleteAll(addressRepository.findByUser(user));
		
		return "success";
	}

	public String deleteAddress(String email,Long addressId) {
		// TODO Auto-generated method stub
		User user = userRepository.findByEmail(email)
		        .orElseThrow(()->new RuntimeException("user not found"));

		Address address = addressRepository.findById(addressId)
		        .orElseThrow(()->new RuntimeException("addres not found"));

		if (!address.getUser().getId().equals(user.getId())) {
		    throw new RuntimeException("Access denied");
		}
		
		addressRepository.delete(address);
		return "success";
	}

	public String updateAddress(String email,Long addressId, AddressUpdateRequest request) {
		// TODO Auto-generated method stub
		User user = userRepository.findByEmail(email)
		        .orElseThrow(()->new RuntimeException("user not found"));

		Address address = addressRepository.findById(addressId)
		        .orElseThrow(()->new RuntimeException("addres not found"));

		if (!address.getUser().getId().equals(user.getId())) {
		    throw new RuntimeException("Access denied");
		}
		
		addressMapper.updateEntity(address, request);
				
		return "success";
	}
	
	
	
}

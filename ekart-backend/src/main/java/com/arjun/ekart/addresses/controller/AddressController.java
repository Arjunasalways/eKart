package com.arjun.ekart.addresses.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arjun.ekart.addresses.dto.AddressCreationRequest;
import com.arjun.ekart.addresses.dto.AddressResponse;
import com.arjun.ekart.addresses.dto.AddressUpdateRequest;
import com.arjun.ekart.addresses.service.AddressService;

@RestController
@RequestMapping("/users")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	
	@PostMapping("/address")
	public AddressResponse addAddress(Authentication auth,@RequestBody AddressCreationRequest request) {
		
		String email = auth.getName();
		return addressService.addAddress(email,request);
	}
	
	@GetMapping("/address")
	public List<AddressResponse> getAllAddress(Authentication auth) {
		return addressService.getAllAddresses(auth.getName());
	}
	
	
	@GetMapping("/address/{addressId}")
	public AddressResponse getAddress(Authentication auth,@PathVariable Long addressId) {
		return addressService.getAddress(auth.getName(),addressId);
	}
	
	@DeleteMapping("/address")
	public String deleteAllAddress(Authentication auth) {
		return addressService.deleteAllAddresses(auth.getName());
	}
	
	@DeleteMapping("/address/{addressId}")
	public String deleteAddress(Authentication auth,@PathVariable Long addressId) {
		return addressService.deleteAddress(auth.getName(),addressId);
	}
	
	@PutMapping("/address/{addressId}")
	public String updateAddress(Authentication auth,@PathVariable Long addressId,@RequestBody AddressUpdateRequest request) {
		return addressService.updateAddress(auth.getName(),addressId,request);
	}
	
	
}

package com.arjun.ekart.orders.dto;


import jakarta.validation.constraints.NotNull;

public class CartCheckOutRequest {
	
	
	@NotNull
	private Long addressId;

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	
	
	
}

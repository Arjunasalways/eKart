package com.arjun.ekart.cart.dto;

import java.math.BigDecimal;
import java.util.List;

import com.arjun.ekart.cartitems.dto.CartItemResponse;

public class CartResponse {
	
	
	private List<CartItemResponse> cartItems;
	
	private BigDecimal grandTotal;
	
	private int totalItems;

	public List<CartItemResponse> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItemResponse> cartItems) {
		this.cartItems = cartItems;
	}

	public BigDecimal getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(BigDecimal grandTotal) {
		this.grandTotal = grandTotal;
	}

	public int getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}
	
	
	
}

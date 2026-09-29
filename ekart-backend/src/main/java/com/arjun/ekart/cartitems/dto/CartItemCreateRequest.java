package com.arjun.ekart.cartitems.dto;


public class CartItemCreateRequest {
	
	private int quantity;
	
	private Long productVariantId;

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Long getProductVariantId() {
		return productVariantId;
	}

	public void setProductVariantId(Long productVariantId) {
		this.productVariantId = productVariantId;
	}
	
	
}

package com.arjun.ekart.orderitems.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class OrderItemCreateRequest {
	
	@Min(1)
	@NotNull
	private Integer quantity;
	
	@NotNull
	private Long variantId;

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Long getVariantId() {
		return variantId;
	}

	public void setVariantId(Long variantId) {
		this.variantId = variantId;
	}	
	
	
	
}

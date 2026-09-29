package com.arjun.ekart.productvariants.dto;

import java.math.BigDecimal;


public class ProductVariantUpdateRequest {
	
	
	private BigDecimal price;
	
	private Integer stockQuantity;
		
	private Boolean active;

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(Integer stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
	
}

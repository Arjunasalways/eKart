package com.arjun.ekart.productvariants.dto;

import java.math.BigDecimal;
import java.util.List;

import com.arjun.ekart.productattributes.dto.ProductAttributeResponse;

public class ProductVariantResponse {
	
	private String sku;

	private BigDecimal price;
	
	private Integer stockQuantity;
	
	private boolean active;
		
	private List<ProductAttributeResponse> attributes;
	
	private String thumbnail;
	
	
	
	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<ProductAttributeResponse> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<ProductAttributeResponse> attributes) {
		this.attributes = attributes;
	}
}

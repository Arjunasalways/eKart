package com.arjun.ekart.productvariants.dto;

import java.math.BigDecimal;
import java.util.List;

import com.arjun.ekart.productattributes.dto.ProductAttributeCreateRequest;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ProductVariantCreateRequest {
	
	private String sku;

	@NotNull
	@DecimalMin(value ="0.01")
	private BigDecimal price;
	
	@NotNull
	@Min(0)
	private Integer stockQuantity;
	
	@NotNull
	private Long productId;
	
	private Boolean active;	

	private List<ProductAttributeCreateRequest> attributes;

	public List<ProductAttributeCreateRequest> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<ProductAttributeCreateRequest> attributes) {
		this.attributes = attributes;
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

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	
}

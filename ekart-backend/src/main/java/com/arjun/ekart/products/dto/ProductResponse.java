package com.arjun.ekart.products.dto;

import java.time.LocalDateTime;

import com.arjun.ekart.categories.dto.CategoryResponse;
import com.arjun.ekart.productvariants.dto.ProductVariantResponse;


public class ProductResponse {
	
	private Long id;
	
	private String name;
	
	private String description;
	
	private String brand;
	
	private CategoryResponse category;
	
	private boolean active;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
	private ProductVariantResponse defaultVariant;
	
	
	
	
	
	public ProductVariantResponse getDefaultVariant() {
		return defaultVariant;
	}

	public void setDefaultVariant(ProductVariantResponse defaultVariant) {
		this.defaultVariant = defaultVariant;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public CategoryResponse getCategory() {
		return category;
	}

	public void setCategory(CategoryResponse category) {
		this.category = category;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
}

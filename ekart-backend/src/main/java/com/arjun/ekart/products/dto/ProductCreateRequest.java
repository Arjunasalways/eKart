package com.arjun.ekart.products.dto;

import java.time.LocalDateTime;

import jakarta.validation.Valid;


public class ProductCreateRequest {
	
	private String name;
	
	private String description;
	
	private String brand;
	
	@Valid
	private Long category;
	
	private Boolean active;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
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
	
	public Long getCategory() {
		return category;
	}
	
	public void setCategory(Long category) {
		this.category = category;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
}

package com.arjun.ekart.products.dto;


public class ProductUpdateRequest {
	
	private String name;
	
	private String description;
	
	private Boolean active;
	
	private String brand;
	
	private Long categoryid;

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

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Long getCategory() {
		return categoryid;
	}

	public void setCategory(Long category) {
		this.categoryid = category;
	}

}

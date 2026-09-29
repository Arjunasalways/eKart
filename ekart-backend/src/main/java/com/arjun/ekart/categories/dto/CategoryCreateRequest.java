package com.arjun.ekart.categories.dto;

import jakarta.validation.constraints.NotBlank;

public class CategoryCreateRequest {
	
	@NotBlank(message = "name cannot be blank")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}

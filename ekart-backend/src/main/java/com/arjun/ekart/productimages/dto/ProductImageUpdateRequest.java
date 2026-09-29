package com.arjun.ekart.productimages.dto;

public class ProductImageUpdateRequest {
	
	private String imageUrl;
	
	private Boolean primaryImage;

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Boolean getPrimaryImage() {
		return primaryImage;
	}

	public void setPrimaryImage(Boolean primaryImage) {
		this.primaryImage = primaryImage;
	}
	
}

package com.arjun.ekart.productimages.dto;


public class ProductImageResponse {
	
	private Long productVariantId;
	
	private String imageUrl;
	
	private boolean primaryImage;
	
	private Long imageId;

	public Long getProductVariantId() {
		return productVariantId;
	}

	public void setProductVariantId(Long productVariantId) {
		this.productVariantId = productVariantId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public boolean isPrimaryImage() {
		return primaryImage;
	}

	public void setPrimaryImage(boolean primaryImage) {
		this.primaryImage = primaryImage;
	}

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}
	
	
}

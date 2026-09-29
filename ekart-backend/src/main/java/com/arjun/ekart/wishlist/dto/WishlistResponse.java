package com.arjun.ekart.wishlist.dto;

import java.util.ArrayList;
import java.util.List;

import com.arjun.ekart.productvariants.dto.ProductVariantResponse;

public class WishlistResponse {
	
	private Long id;
	
	private List<ProductVariantResponse> wishlistItems = new ArrayList<>();
	
	private int totalItems;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<ProductVariantResponse> getWishlistItems() {
		return wishlistItems;
	}

	public void setWishlistItems(List<ProductVariantResponse> wishlistItems) {
		this.wishlistItems = wishlistItems;
	}

	public int getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}
	
	
}

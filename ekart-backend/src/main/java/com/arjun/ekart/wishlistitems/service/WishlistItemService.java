package com.arjun.ekart.wishlistitems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arjun.ekart.productvariants.entity.ProductVariant;
import com.arjun.ekart.productvariants.repository.ProductVariantRepository;
import com.arjun.ekart.wishlist.entity.Wishlist;
import com.arjun.ekart.wishlist.repository.WishlistRepository;
import com.arjun.ekart.wishlistitems.dto.CreateWishlistItemRequest;
import com.arjun.ekart.wishlistitems.entity.WishlistItem;
import com.arjun.ekart.wishlistitems.repository.WishlistItemRepository;

@Service
public class WishlistItemService {
	
	@Autowired
	private WishlistItemRepository wishlistItemRepository;
	
	@Autowired
	private WishlistRepository wishlistRepository;
	
	@Autowired
	private ProductVariantRepository productVariantRepository;

	public String addtoWishlist(CreateWishlistItemRequest request, String email) {
		// TODO Auto-generated method stub
		
		ProductVariant variant = productVariantRepository.findById(request.getProductVariantId())
				.orElseThrow(()->new RuntimeException("variant not exist"));

		
		Wishlist wishlist = wishlistRepository.findByUserEmail(email)
				.orElseThrow(()-> new RuntimeException("wishlist not found"));
		
		WishlistItem existing =
		        wishlistItemRepository.findByWishlistAndVariant(
		                wishlist,
		                variant);

		if (existing != null) {
		    throw new RuntimeException("Already in wishlist");
		}
		
		existing.setVariant(variant);
		
		existing.setWishlist(wishlist);
		
		wishlistItemRepository.save(existing);
		
		return "success";
	}

}

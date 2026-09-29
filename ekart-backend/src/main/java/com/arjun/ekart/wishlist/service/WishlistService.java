package com.arjun.ekart.wishlist.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arjun.ekart.wishlist.dto.WishlistResponse;
import com.arjun.ekart.wishlist.entity.Wishlist;
import com.arjun.ekart.wishlist.mapper.WishlistMapper;
import com.arjun.ekart.wishlist.repository.WishlistRepository;

@Service
public class WishlistService {
	
	@Autowired
	private WishlistRepository wishlistRepository;
	
	@Autowired
	private WishlistMapper wishlistMapper;

	public WishlistResponse getWishlist(String email) {
		// TODO Auto-generated method stub
		
		Wishlist wishlist = wishlistRepository.findByUserEmail(email)
				.orElseThrow(()-> new RuntimeException("wishlist not found"));
		
		WishlistResponse response = wishlistMapper.toResponse(wishlist);
		
		response.setTotalItems(response.getWishlistItems().size());
		
		return response;
	}

}

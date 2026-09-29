package com.arjun.ekart.wishlist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arjun.ekart.wishlist.dto.WishlistResponse;
import com.arjun.ekart.wishlist.service.WishlistService;

@RestController
@RequestMapping("/users/wishlist")
public class WishlistController {
	
	@Autowired
	private WishlistService wishlistService;
	
	@GetMapping
	public WishlistResponse getWishlist(Authentication auth) {
		 return wishlistService.getWishlist(auth.getName());
	}
}

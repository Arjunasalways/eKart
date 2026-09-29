package com.arjun.ekart.wishlistitems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arjun.ekart.wishlistitems.dto.CreateWishlistItemRequest;
import com.arjun.ekart.wishlistitems.service.WishlistItemService;

@RestController
@RequestMapping("/users/wishlistItems")
public class WishlistItemsController {
	
	@Autowired
	private WishlistItemService wishlistItemService;
	
	@PostMapping
	public String addToWishlist(@RequestBody CreateWishlistItemRequest request,
			Authentication auth) {
		return wishlistItemService.addtoWishlist(request,auth.getName());
	}
}

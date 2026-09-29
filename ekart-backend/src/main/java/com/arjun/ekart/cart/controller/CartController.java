package com.arjun.ekart.cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arjun.ekart.cart.dto.CartResponse;
import com.arjun.ekart.cart.service.CartService;

@RestController
@RequestMapping("/users")
public class CartController {
	
	@Autowired
	private CartService cartService;

	@GetMapping("/cart")
	public CartResponse getCart( Authentication auth ) {
		return cartService.getCart(auth);
	}
	
	@DeleteMapping("/cart")
	public String deleteCart( Authentication auth ) {
		return cartService.deleteCart(auth);
	}
	
}

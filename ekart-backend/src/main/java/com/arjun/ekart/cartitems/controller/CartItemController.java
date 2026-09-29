package com.arjun.ekart.cartitems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arjun.ekart.cartitems.dto.CartItemCreateRequest;
import com.arjun.ekart.cartitems.dto.CartItemResponse;
import com.arjun.ekart.cartitems.dto.CartItemUpdateRequest;
import com.arjun.ekart.cartitems.service.CartItemService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class CartItemController {
	
	@Autowired
	private CartItemService cartItemService;
	
	@DeleteMapping("/cart/{cartId}/items/{cartItemId}")
	public String deleteFromCart(@PathVariable Long cartItemId) {
		return cartItemService.deleteFromCart(cartItemId);
	}
	
	@PutMapping("/cart/items/{cartItemId}")
	public CartItemResponse updateCartItem(@PathVariable Long cartItemId,@RequestBody @Valid  CartItemUpdateRequest request) {
		return cartItemService.updateCartItem(cartItemId,request);
	}
	
	@PostMapping("/cart/{cartId}/items")
	public CartItemResponse addToCart(@PathVariable Long id,@RequestBody @Valid CartItemCreateRequest request) {
		return cartItemService.addToCart(id,request);
	}
	
}

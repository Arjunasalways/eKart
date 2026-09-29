package com.arjun.ekart.cart.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.arjun.ekart.cart.dto.CartResponse;
import com.arjun.ekart.cart.entity.Cart;
import com.arjun.ekart.cart.mapper.CartMapper;
import com.arjun.ekart.cart.repository.CartRepository;
import com.arjun.ekart.cartitems.entity.CartItem;
import com.arjun.ekart.cartitems.repository.CartItemRepository;
import com.arjun.ekart.productvariants.entity.ProductVariant;
import com.arjun.ekart.security.models.CustomUserDetails;

@Service
public class CartService {
	
	@Autowired 
	private CartRepository cartRepository;
	
	@Autowired
	private CartMapper cartMapper;
	
	@Autowired CartItemRepository cartItemRepository;
	
	

	public CartResponse getCart(Authentication auth ) {
		// TODO Auto-generated method stub
		
		CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
		
		Cart cart=cartRepository.findByUserId(user.getUserId())
				.orElseThrow(()-> new RuntimeException("cart not found"));
		
		CartResponse response=cartMapper.toResponse(cart);
		
		BigDecimal grandTotal=BigDecimal.ZERO;
		int totalItems=0;
		
		for(CartItem cartItem:cart.getCartItems()) {
			ProductVariant variant=cartItem.getProductVariant();
			grandTotal=grandTotal.add(variant.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));
			totalItems+=cartItem.getQuantity();
			
		}
		
		response.setGrandTotal(grandTotal);
		response.setTotalItems(totalItems);
		
		return response;
	}

	public String deleteCart(Authentication auth) {
		// TODO Auto-generated method stub
		
		CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
		
		Cart cart=cartRepository.findByUserId(user.getUserId())
				.orElseThrow(()-> new RuntimeException("cart not found"));
		
		cart.getCartItems().clear();
		
		cartRepository.save(cart);
		return "success";
	}

	
}

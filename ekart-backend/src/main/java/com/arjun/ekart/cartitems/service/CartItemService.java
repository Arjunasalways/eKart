package com.arjun.ekart.cartitems.service;

import java.math.BigDecimal;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arjun.ekart.cart.entity.Cart;
import com.arjun.ekart.cart.repository.CartRepository;
import com.arjun.ekart.cartitems.dto.CartItemCreateRequest;
import com.arjun.ekart.cartitems.dto.CartItemResponse;
import com.arjun.ekart.cartitems.dto.CartItemUpdateRequest;
import com.arjun.ekart.cartitems.entity.CartItem;
import com.arjun.ekart.cartitems.mapper.CartItemMapper;
import com.arjun.ekart.cartitems.repository.CartItemRepository;
import com.arjun.ekart.productimages.entity.ProductImage;
import com.arjun.ekart.productvariants.entity.ProductVariant;
import com.arjun.ekart.productvariants.repository.ProductVariantRepository;

@Service
public class CartItemService {
	
	@Autowired
	private ProductVariantRepository productVariantRepository;
	
	@Autowired
	private CartItemMapper cartItemMapper;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private CartItemRepository cartItemRepository;

	public CartItemResponse addToCart(Long cartId,CartItemCreateRequest request) {
		// TODO Auto-generated method stub
		ProductVariant variant=productVariantRepository.findById(request.getProductVariantId())
				.orElseThrow(()->new RuntimeException("variant not found"));
				
		Cart cart=cartRepository.findById(cartId)
				.orElseThrow(()-> new RuntimeException("Cart not found"));
		
		
		Optional<CartItem> existingCartItem=cartItemRepository.findByCartAndProductVariant(cart,variant);
		
		CartItem cartItem;
		
		if(existingCartItem.isPresent()) {
			
			cartItem=existingCartItem.get();
			
			int newQuantity=cartItem.getQuantity()+request.getQuantity();
			
			validateAndSetQuantity(newQuantity,variant,cartItem);
		}
		
		else{
			
			cartItem=new CartItem();
			
			cartItem.setProductVariant(variant);
			
			validateAndSetQuantity(request.getQuantity(),variant,cartItem);
			
			cartItem.setCart(cart);
		
		}
		
		cartItemRepository.save(cartItem);
		
		return buildResponse(cartItem);
	}

	public String deleteFromCart(Long cartItemId) {
		// TODO Auto-generated method stub
		CartItem cartItem=cartItemRepository.findById(cartItemId)
				.orElseThrow(()-> new RuntimeException("cartitem not found"));
		cartItemRepository.delete(cartItem);
		return "succcess";
	}

	public CartItemResponse updateCartItem(Long cartItemId, CartItemUpdateRequest request) {
		// TODO Auto-generated method stub
		CartItem cartItem=cartItemRepository.findById(cartItemId)
				.orElseThrow(()-> new RuntimeException("cartitem not found"));
		
		ProductVariant variant=cartItem.getProductVariant();
		
		int newQuantity=request.getQuantity();
		
		validateAndSetQuantity(newQuantity,variant,cartItem);
		
		cartItemRepository.save(cartItem);
		
		return buildResponse(cartItem);
	}
	
	private void validateAndSetQuantity(int newQuantity,ProductVariant variant,CartItem cartItem) {
		
		if(newQuantity>variant.getStockQuantity()) {
			
			throw new RuntimeException("Only" + variant.getStockQuantity() + " item(s) available in stock.");
		}
		
		cartItem.setQuantity(newQuantity);
		
	}
	
	private CartItemResponse buildResponse(CartItem cartItem) {
	    CartItemResponse response = cartItemMapper.toResponse(cartItem);

	    ProductVariant variant = cartItem.getProductVariant();

	    ProductImage image = variant.getImages()
	            .stream()
	            .filter(ProductImage::isPrimaryImage)
	            .findFirst()
	            .orElse(null);

	    if (image != null) {
	        response.setThumbnail(image.getImageUrl());
	    }

	    response.setSubTotal(
	        variant.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity()))
	    );

	    return response;
	}

}

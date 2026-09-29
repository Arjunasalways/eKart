package com.arjun.ekart.cartitems.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.arjun.ekart.cartitems.dto.CartItemCreateRequest;
import com.arjun.ekart.cartitems.dto.CartItemResponse;
import com.arjun.ekart.cartitems.entity.CartItem;


@Mapper(componentModel="spring")
public interface CartItemMapper  {
	
	@Mapping(target="name",source="productVariant.product.name")
	@Mapping(target="thumbnail",ignore=true)
	@Mapping(target="price",source="productVariant.price")
	@Mapping(target="subTotal",ignore=true)
	CartItemResponse toResponse(CartItem cartItem);	
	
		
}

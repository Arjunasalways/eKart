package com.arjun.ekart.cart.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.arjun.ekart.cart.dto.CartResponse;
import com.arjun.ekart.cart.entity.Cart;
import com.arjun.ekart.cartitems.mapper.CartItemMapper;

@Mapper(
		componentModel="spring",
		uses=CartItemMapper.class
)
public interface CartMapper {
	
	@Mapping(target="grandTotal" , ignore = true)
	@Mapping(target="totalItems", ignore= true)
	CartResponse toResponse(Cart cart);
	
}

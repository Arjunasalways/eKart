package com.arjun.ekart.wishlist.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.arjun.ekart.productvariants.productvariantmapper.ProductVariantMapper;
import com.arjun.ekart.wishlist.dto.WishlistResponse;
import com.arjun.ekart.wishlist.entity.Wishlist;

@Mapper(componentModel = "spring",
uses = ProductVariantMapper.class)
public interface WishlistMapper {

	@Mapping(target="totalItems" , ignore = true)
	WishlistResponse toResponse(Wishlist wishlist);
}

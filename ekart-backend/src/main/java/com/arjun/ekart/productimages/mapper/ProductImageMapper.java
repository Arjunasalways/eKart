package com.arjun.ekart.productimages.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.arjun.ekart.productimages.dto.ProductImageResponse;
import com.arjun.ekart.productimages.entity.ProductImage;

@Mapper(componentModel = "spring")
public interface ProductImageMapper {
	
	@Mapping(source="productVariant.id" , target="productVariantId")
	@Mapping(source = "id" , target="imageId")
	ProductImageResponse toResponse(ProductImage productImage);
	
	
}

package com.arjun.ekart.productattributes.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.arjun.ekart.productattributes.dto.ProductAttributeCreateRequest;
import com.arjun.ekart.productattributes.dto.ProductAttributeResponse;
import com.arjun.ekart.productattributes.entity.ProductAttribute;

@Mapper(componentModel ="spring")
public interface ProductAttributeMapper {
	
	ProductAttributeResponse toResponse(ProductAttribute productAttribute); 
	
	@Mapping(target = "productVariant", ignore = true)
	ProductAttribute toEntity(ProductAttributeCreateRequest request);
}

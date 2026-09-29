package com.arjun.ekart.productvariants.productvariantmapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.arjun.ekart.productattributes.mapper.ProductAttributeMapper;
import com.arjun.ekart.productimages.mapper.ProductImageMapper;
import com.arjun.ekart.products.mapper.ProductMapper;
import com.arjun.ekart.productvariants.dto.ProductVariantCreateRequest;
import com.arjun.ekart.productvariants.dto.ProductVariantDetailResponse;
import com.arjun.ekart.productvariants.dto.ProductVariantResponse;
import com.arjun.ekart.productvariants.dto.ProductVariantUpdateRequest;
import com.arjun.ekart.productvariants.entity.ProductVariant;


@Mapper(componentModel = "spring",
		uses = {
				ProductMapper.class,
				ProductAttributeMapper.class,
				ProductImageMapper.class
			}
)
public interface ProductVariantMapper {
	
	@BeanMapping(
			nullValuePropertyMappingStrategy=NullValuePropertyMappingStrategy.IGNORE
			)
	
	@Mapping(target="product" , ignore = true)
	ProductVariant toEntity(ProductVariantCreateRequest request);
	
	ProductVariantResponse toResponse(ProductVariant productVariant);

	ProductVariantDetailResponse toDetailResponse(ProductVariant productVariant);
	
	@Mapping(target= "product" , ignore = true)
	void updateEntity(ProductVariantUpdateRequest request,@MappingTarget ProductVariant productVariant);
	
}

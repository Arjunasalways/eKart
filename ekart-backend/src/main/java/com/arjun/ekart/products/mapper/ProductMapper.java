package com.arjun.ekart.products.mapper;

import org.mapstruct.BeanMapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.arjun.ekart.categories.mapper.CategoryMapper;
import com.arjun.ekart.products.dto.ProductCreateRequest;
import com.arjun.ekart.products.dto.ProductDetailResponse;
import com.arjun.ekart.products.dto.ProductResponse;
import com.arjun.ekart.products.dto.ProductUpdateRequest;
import com.arjun.ekart.products.entity.Product;
import com.arjun.ekart.productvariants.productvariantmapper.ProductVariantMapper;

@Mapper(componentModel = "spring",
		uses = {
			CategoryMapper.class,
			ProductVariantMapper.class
		}
)
public interface ProductMapper {
	
	 @Mapping(target="category" , ignore=true)
	 Product toEntity(ProductCreateRequest request);

	 @Mapping(target="defaultVariant", ignore = true)
	 ProductResponse toResponse(Product product);

	 @Mapping(target="category" , ignore=true)
	 @BeanMapping(
				nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
				)
     void updateEntity(ProductUpdateRequest request,@MappingTarget Product product);


	 ProductDetailResponse toDetailResponse(Product product);
}

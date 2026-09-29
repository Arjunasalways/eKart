package com.arjun.ekart.categories.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.arjun.ekart.categories.dto.CategoryCreateRequest;
import com.arjun.ekart.categories.dto.CategoryResponse;
import com.arjun.ekart.categories.dto.CategoryUpdateRequest;
import com.arjun.ekart.categories.entity.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

	
	
	Category toEntity(CategoryCreateRequest category);
	
	CategoryResponse toResponse(Category category);
	
	@BeanMapping(
			nullValuePropertyMappingStrategy=NullValuePropertyMappingStrategy.IGNORE
			)	
	void updateEntity(CategoryUpdateRequest request,@MappingTarget Category category);
}

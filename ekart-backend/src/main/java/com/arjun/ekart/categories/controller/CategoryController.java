package com.arjun.ekart.categories.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arjun.ekart.categories.dto.CategoryResponse;
import com.arjun.ekart.categories.service.CategoryService;
import com.arjun.ekart.pagination.dto.PageResponse;

@RestController
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/categories")
	public PageResponse<CategoryResponse> getAllCategories(
			@RequestParam(required = false) String search,  
			Pageable pageable ) {

		return categoryService.getAllCategories(search,pageable);
		
	}
	
	@GetMapping("/categories/{id}")
	public CategoryResponse getCategory( @PathVariable Long id ) {

		return categoryService.getCategory(id);
		
	}
	
	
	
}

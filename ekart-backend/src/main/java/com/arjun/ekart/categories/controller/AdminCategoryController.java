package com.arjun.ekart.categories.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arjun.ekart.categories.dto.CategoryCreateRequest;
import com.arjun.ekart.categories.dto.CategoryResponse;
import com.arjun.ekart.categories.dto.CategoryUpdateRequest;
import com.arjun.ekart.categories.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminCategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/categories")
	public CategoryResponse addCategory(@RequestBody @Valid CategoryCreateRequest request ) {
		
		return categoryService.addCategory(request);

	}
	
	@DeleteMapping("/categories/delete/{id}")
	public String deleteCategory(@PathVariable Long id) {
		return categoryService.deleteCategory(id);
	}

	@DeleteMapping("/categories")
	public String deleteAllCategories() {
		return categoryService.deleteAllCategories();
	}
	
	@PutMapping("/categories/update/{id}")
	public CategoryResponse updateCategory(@PathVariable Long id,@Valid @RequestBody CategoryUpdateRequest request) {
		return categoryService.updateCategory(id,request);
	}
}

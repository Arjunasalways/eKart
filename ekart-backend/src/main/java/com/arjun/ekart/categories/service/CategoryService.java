package com.arjun.ekart.categories.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.arjun.ekart.categories.dto.CategoryCreateRequest;
import com.arjun.ekart.categories.dto.CategoryResponse;
import com.arjun.ekart.categories.dto.CategoryUpdateRequest;
import com.arjun.ekart.categories.entity.Category;
import com.arjun.ekart.categories.mapper.CategoryMapper;
import com.arjun.ekart.categories.repository.CategoryRepository;
import com.arjun.ekart.pagination.dto.PageResponse;
import com.arjun.ekart.pagination.mapper.PageMapper;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private CategoryMapper categoryMapper;
	
	@Autowired
	private PageMapper pageMapper;
	
	public PageResponse<CategoryResponse> getAllCategories(String search,Pageable pageable) {
		// TODO Auto-generated method stub
		Page<Category> categories;
		
		if( search == null || search.isBlank()) {
				categories = categoryRepository.findAll(pageable);
				
		}
		
		else {
			
			categories = categoryRepository.findByNameContainingIgnoreCase(search, pageable);
		}
		
		Page<CategoryResponse> response = categories.map(categoryMapper::toResponse);
		
		return pageMapper.toResponse(response);
	}
	
	public CategoryResponse getCategory(Long id) {
		
		
		Category category=categoryRepository.findById(id)
				.orElseThrow(()->new RuntimeException("category not found"));
		
		
	return categoryMapper.toResponse(category);
				
	}
	
	public CategoryResponse addCategory(CategoryCreateRequest request) {
		
		Optional<Category> existingCategory=categoryRepository.findByName(request.getName());
		
		if(existingCategory.isPresent()) {
			throw new RuntimeException("Category exists");
		}
		
		Category category=categoryMapper.toEntity(request);
						
		categoryRepository.save(category);	
		
		return categoryMapper.toResponse(category);
	}

	public String deleteCategory(Long id) {
		// TODO Auto-generated method stub
		
		Category category=categoryRepository.findById(id)
				.orElseThrow(() ->  new RuntimeException("Cateogry Doesn't exist"));
		
		categoryRepository.delete(category);
		
		return "delete succesfull";
	}

	public String deleteAllCategories() {
		// TODO Auto-generated method stub
		
		categoryRepository.deleteAll();
		
		return "delete succesfull";
	}
	
	public CategoryResponse updateCategory(Long id ,CategoryUpdateRequest request) {
		
		Category category=categoryRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("Item no longer exists"));
		
		Optional<Category> existingCategory=categoryRepository.findByName(request.getName());
		
		if(existingCategory.isPresent()&&!existingCategory.get().getId().equals(id)) {
			throw new RuntimeException("category name already existe.Please choose a new name.");
		}
		
		
		categoryMapper.updateEntity(request,category);
		
		categoryRepository.save(category);
		
		return categoryMapper.toResponse(category);
	}

	
}

package com.arjun.ekart.products.controller;

import java.math.BigDecimal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.arjun.ekart.pagination.dto.PageResponse;
import com.arjun.ekart.products.dto.ProductDetailResponse;
import com.arjun.ekart.products.dto.ProductResponse;
import com.arjun.ekart.products.service.ProductService;


@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@GetMapping
	public PageResponse<ProductResponse> getAllProducts(
			@PageableDefault(
	                page = 0,
	                size = 20,
	                sort = "name"
	        )
			Pageable pageable,
			@RequestParam(required = false) String search,
			@RequestParam(required = false) List<String> brand,
			@RequestParam(required = false) Long categoryId,
			@RequestParam(required = false) BigDecimal lower,
			@RequestParam(required = false) BigDecimal higher){
			
		return productService.getAllProducts(
				pageable,
				search,
				brand,
				categoryId,
				lower,
				higher);
	}
	
	@GetMapping("/{id}")
	public ProductDetailResponse getProduct(@PathVariable Long id) {
		return productService.getProduct(id);
	}
	
	@GetMapping("/categories/{categoryId}")
	public PageResponse<ProductResponse> getAllProductsByCategory( Pageable pageable,@PathVariable Long categoryId ) {

		return productService.getAllProductsByCategory(pageable,categoryId);
		
	}
	
	
}

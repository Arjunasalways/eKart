package com.arjun.ekart.productvariants.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arjun.ekart.productvariants.dto.ProductVariantCreateRequest;
import com.arjun.ekart.productvariants.dto.ProductVariantDetailResponse;
import com.arjun.ekart.productvariants.dto.ProductVariantResponse;
import com.arjun.ekart.productvariants.dto.ProductVariantUpdateRequest;
import com.arjun.ekart.productvariants.service.ProductVariantService;


@RestController
@RequestMapping("/products/{id}")
public class ProductVariantController {
	
	@Autowired
	private ProductVariantService productVariantService;
	
	@GetMapping("/productvariant")
	public List<ProductVariantResponse> getProductVariants(@PathVariable Long id) {
		return productVariantService.getProductVariants(id);
	}
	
	@GetMapping("/productvariants")
	public List<ProductVariantDetailResponse> getAllProductVariants(@PathVariable Long id) {
		return productVariantService.getAllProductVariants(id);
	}

	@GetMapping("/productvariants/{pid}")
	public ProductVariantDetailResponse getProductVariant(@PathVariable Long pid) {
		return productVariantService.getProductVariant(pid);
	}
	
}

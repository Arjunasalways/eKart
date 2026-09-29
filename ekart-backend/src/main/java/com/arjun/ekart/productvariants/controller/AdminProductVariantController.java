package com.arjun.ekart.productvariants.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arjun.ekart.productvariants.dto.ProductVariantCreateRequest;
import com.arjun.ekart.productvariants.dto.ProductVariantResponse;
import com.arjun.ekart.productvariants.dto.ProductVariantUpdateRequest;
import com.arjun.ekart.productvariants.service.ProductVariantService;

@RestController
@RequestMapping("/admin/products/{id}")
public class AdminProductVariantController {

	
	@Autowired
	private ProductVariantService productVariantService;
	

	@PutMapping("/productvariants/{pid}")
	public ProductVariantResponse updateProductVariant(@PathVariable Long pid , @RequestBody ProductVariantUpdateRequest request) {
		return productVariantService.updateProductVariant(pid,request);
	}
	
	@DeleteMapping("/productvariants/{pid}")
	public String deleteProductVariant(@PathVariable Long pid) {
		return productVariantService.deleteProductVariant(pid);
	}
	
	@DeleteMapping("/productvariants")
	public String deleteAllProductVariants(@PathVariable Long id) {
		return productVariantService.deleteAllProductVariants(id);
	}
	
	@PostMapping("/productvariants")
	public ProductVariantResponse createProductVariant(@RequestBody ProductVariantCreateRequest request) {
		return productVariantService.createProductVariant(request);
	}
	
}

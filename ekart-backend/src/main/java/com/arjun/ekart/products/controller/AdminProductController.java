package com.arjun.ekart.products.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arjun.ekart.products.dto.ProductCreateRequest;
import com.arjun.ekart.products.dto.ProductResponse;
import com.arjun.ekart.products.dto.ProductUpdateRequest;
import com.arjun.ekart.products.service.ProductService;


@RestController
@RequestMapping("/admin")
public class AdminProductController {
	
	@Autowired
	private ProductService productService;

	
	@PutMapping("/products/{id}")
	public ProductResponse updateProduct(@PathVariable Long id , @RequestBody ProductUpdateRequest request) {
		return productService.updateProduct(id,request);
	}
	
	@DeleteMapping("/products/{id}")
	public String deleteProduct(@PathVariable Long id) {
		return productService.deleteProduct(id);
	}
	
	@DeleteMapping("/products")
	public String deleteAllProducts() {
		return productService.deleteAllProducts();
	}
	
	@PostMapping("/products")
	public ProductResponse createProduct(@RequestBody ProductCreateRequest request) {
		
		System.out.print(request.getCategory());
		return productService.createProduct(request);
	}
}

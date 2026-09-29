package com.arjun.ekart.productvariants.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arjun.ekart.productvariants.dto.ProductVariantDetailResponse;
import com.arjun.ekart.productvariants.service.VariantService;

@RestController
@RequestMapping("/products/variants")
public class VariantController {
	
	@Autowired
	private VariantService variantService;
	
	
	@DeleteMapping
	public String deleteAllVariants() {
		return variantService.deleteAllVariants();
	}
	
	@GetMapping
	public List<ProductVariantDetailResponse> getAllVariants() {
		return variantService.getAllVariants();
	}
}

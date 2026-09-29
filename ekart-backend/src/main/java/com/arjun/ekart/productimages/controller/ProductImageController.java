package com.arjun.ekart.productimages.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.arjun.ekart.productimages.dto.ProductImageResponse;
import com.arjun.ekart.productimages.service.ProductImageService;

@RestController
@RequestMapping("/product-variants/{variantId}/images")
public class ProductImageController {
	
	@Autowired 
	private ProductImageService productImageService;

	@GetMapping
	public List<ProductImageResponse> getImages(@PathVariable Long variantId) {
		return productImageService.getImages(variantId);
	}
}

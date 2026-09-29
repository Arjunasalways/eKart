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
@RequestMapping("/admin/product-variants/{variantId}/images")
public class AdminProductImageController {
	
	@Autowired 
	private ProductImageService productImageService;

	@GetMapping
	public List<ProductImageResponse> getImages(@PathVariable Long variantId) {
		return productImageService.getImages(variantId);
	}

	@PostMapping
	public ProductImageResponse uploadImage(
			@PathVariable Long variantId,
			@RequestParam MultipartFile file,
			@RequestParam boolean primaryImage) {
		return productImageService.uploadImage(variantId,file,primaryImage);
	}
	
	
	@DeleteMapping
	public String deleteImages(@PathVariable Long variantId) {
		return productImageService.deleteImages(variantId);
	}

	@DeleteMapping("/{imageId}")
	public String deleteImage(@PathVariable Long imageId,
			@PathVariable Long variantId) {
		return productImageService.deleteImage(variantId,imageId);
	}
	
	@PutMapping("/{imageId}")
	public ProductImageResponse updateImage(
			@PathVariable Long variantId,
			@PathVariable Long imageId,
			@RequestParam(required=false) MultipartFile file,
			@RequestParam boolean primaryImage) {
		return productImageService.updateImage(variantId, imageId,  file , primaryImage);
	}
	
}

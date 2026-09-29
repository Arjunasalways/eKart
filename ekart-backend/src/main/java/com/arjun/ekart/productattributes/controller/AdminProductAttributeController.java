package com.arjun.ekart.productattributes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arjun.ekart.productattributes.dto.ProductAttributeCreateRequest;
import com.arjun.ekart.productattributes.dto.ProductAttributeResponse;
import com.arjun.ekart.productattributes.service.ProductAttributeService;

@RestController
@RequestMapping("/admin/variants/{variantId}/attributes")
public class AdminProductAttributeController {
	
	@Autowired
    private ProductAttributeService productAttributeService;

	@PostMapping
    public ProductAttributeResponse addAttribute(
            @PathVariable Long variantId,
            @RequestBody ProductAttributeCreateRequest request) {

        return productAttributeService.addAttribute(variantId, request);
    }

	@PutMapping("/{attributeId}")
    public ProductAttributeResponse updateAttribute(
            @PathVariable Long variantId,
            @PathVariable Long attributeId,
            @RequestBody ProductAttributeCreateRequest request) {

        return productAttributeService.updateAttribute(
                variantId,
                attributeId,
                request);
    }

    @DeleteMapping("/{attributeId}")
    public String deleteAttribute(
            @PathVariable Long variantId,
            @PathVariable Long attributeId) {

        return productAttributeService.deleteAttribute(
                variantId,
                attributeId);
    }
}

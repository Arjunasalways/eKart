package com.arjun.ekart.productattributes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.arjun.ekart.productattributes.dto.ProductAttributeCreateRequest;
import com.arjun.ekart.productattributes.dto.ProductAttributeResponse;
import com.arjun.ekart.productattributes.service.ProductAttributeService;

@RestController
@RequestMapping("/variants/{variantId}/attributes")
public class ProductAttributeController {

    @Autowired
    private ProductAttributeService productAttributeService;

    
    @GetMapping
    public List<ProductAttributeResponse> getAttributes(
            @PathVariable Long variantId) {

        return productAttributeService.getAttributes(variantId);
    }

    
}
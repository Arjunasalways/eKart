package com.arjun.ekart.productattributes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.arjun.ekart.productattributes.dto.ProductAttributeCreateRequest;
import com.arjun.ekart.productattributes.dto.ProductAttributeResponse;
import com.arjun.ekart.productattributes.service.ProductAttributeService;

@RestController
@RequestMapping("/admin/attributes")
public class AttributeController {

    @Autowired
    private ProductAttributeService productAttributeService;

    @PostMapping("/bulk")
    public List<ProductAttributeResponse> addAttributes(
            @RequestBody List<ProductAttributeCreateRequest> requests) {

        return productAttributeService.addAttributes(requests);
    }
    
    @GetMapping("/bulk")
    public List<ProductAttributeResponse> getAttributes() {

        return productAttributeService.getAttributes();
    }
}
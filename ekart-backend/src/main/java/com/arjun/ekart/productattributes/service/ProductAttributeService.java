package com.arjun.ekart.productattributes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arjun.ekart.productattributes.dto.ProductAttributeCreateRequest;
import com.arjun.ekart.productattributes.dto.ProductAttributeResponse;
import com.arjun.ekart.productattributes.entity.ProductAttribute;
import com.arjun.ekart.productattributes.mapper.ProductAttributeMapper;
import com.arjun.ekart.productattributes.repository.ProductAttributeRepository;
import com.arjun.ekart.productvariants.entity.ProductVariant;
import com.arjun.ekart.productvariants.repository.ProductVariantRepository;

@Service
public class ProductAttributeService {

    @Autowired
    private ProductAttributeRepository productAttributeRepository;

    @Autowired
    private ProductVariantRepository productVariantRepository;

    @Autowired
    private ProductAttributeMapper productAttributeMapper;

    public ProductAttributeResponse addAttribute(Long variantId,
            ProductAttributeCreateRequest request) {

        ProductVariant variant = productVariantRepository.findById(variantId)
                .orElseThrow(() -> new RuntimeException("Variant not found"));

        ProductAttribute attribute = productAttributeMapper.toEntity(request);

        attribute.setProductVariant(variant);

        ProductAttribute saved = productAttributeRepository.save(attribute);

        return productAttributeMapper.toResponse(saved);
    }

    public List<ProductAttributeResponse> getAttributes(Long variantId) {

        ProductVariant variant = productVariantRepository.findById(variantId)
                .orElseThrow(() -> new RuntimeException("Variant not found"));

        return variant.getAttributes()
                .stream()
                .map(productAttributeMapper::toResponse)
                .toList();
    }

    public ProductAttributeResponse updateAttribute(Long variantId,
            Long attributeId,
            ProductAttributeCreateRequest request) {

        ProductVariant variant = productVariantRepository.findById(variantId)
                .orElseThrow(() -> new RuntimeException("Variant not found"));

        ProductAttribute attribute = productAttributeRepository.findById(attributeId)
                .orElseThrow(() -> new RuntimeException("Attribute not found"));

        if (!attribute.getProductVariant().getId().equals(variantId)) {
            throw new RuntimeException("Attribute does not belong to this variant");
        }

        attribute.setAttributeName(request.getAttributeName());
        attribute.setAttributeValue(request.getAttributeValue());

        ProductAttribute saved = productAttributeRepository.save(attribute);

        return productAttributeMapper.toResponse(saved);
    }

    public String deleteAttribute(Long variantId, Long attributeId) {

        ProductVariant variant = productVariantRepository.findById(variantId)
                .orElseThrow(() -> new RuntimeException("Variant not found"));

        ProductAttribute attribute = productAttributeRepository.findById(attributeId)
                .orElseThrow(() -> new RuntimeException("Attribute not found"));

        if (!attribute.getProductVariant().getId().equals(variantId)) {
            throw new RuntimeException("Attribute does not belong to this variant");
        }

        productAttributeRepository.delete(attribute);

        return "Attribute deleted successfully";
    }
    
    public List<ProductAttributeResponse> addAttributes(
            List<ProductAttributeCreateRequest> requests) {

        return requests.stream()
                .map(request -> addAttribute(request.getVariantId(), request))
                .toList();
    }

	public List<ProductAttributeResponse> getAttributes() {
		// TODO Auto-generated method stub
		return productAttributeRepository.findAll()
				.stream()
				.map(productAttributeMapper::toResponse)
				.toList();
	}
}
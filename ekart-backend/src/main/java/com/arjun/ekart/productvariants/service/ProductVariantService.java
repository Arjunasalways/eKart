package com.arjun.ekart.productvariants.service;
import java.time.LocalDateTime;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arjun.ekart.productattributes.entity.ProductAttribute;
import com.arjun.ekart.productimages.entity.ProductImage;
import com.arjun.ekart.products.entity.Product;
import com.arjun.ekart.products.repository.ProductRepository;
import com.arjun.ekart.productvariants.dto.ProductVariantCreateRequest;
import com.arjun.ekart.productvariants.dto.ProductVariantDetailResponse;
import com.arjun.ekart.productvariants.dto.ProductVariantResponse;
import com.arjun.ekart.productvariants.dto.ProductVariantUpdateRequest;
import com.arjun.ekart.productvariants.entity.ProductVariant;
import com.arjun.ekart.productvariants.productvariantmapper.ProductVariantMapper;
import com.arjun.ekart.productvariants.repository.ProductVariantRepository;





@Service
public class ProductVariantService {
	
	@Autowired
	private ProductVariantRepository productVariantRepository;
		
	@Autowired 
	private ProductVariantMapper productVariantMapper;
	
	@Autowired
	private ProductRepository productRepository;

	public ProductVariantDetailResponse getProductVariant(Long id) {
		// TODO Auto-generated method stub	
		ProductVariant productVariant=productVariantRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("product not found"));
		
		return productVariantMapper.toDetailResponse(productVariant);
	}

	public ProductVariantResponse updateProductVariant(Long id, ProductVariantUpdateRequest request) {
		// TODO Auto-generated method stub
		
		
		ProductVariant productVariant=productVariantRepository.findById(id)
				.orElseThrow( ()-> new RuntimeException("productVariant not found") );
		
		productVariantMapper.updateEntity(request,productVariant);
		
		
		productVariant.setUpdatedAt(LocalDateTime.now());
				
		productVariantRepository.save(productVariant);
		
		return productVariantMapper.toResponse(productVariant);
	}

	public String deleteProductVariant(Long id) {
		// TODO Auto-generated method stub
		ProductVariant productVariant=productVariantRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("productVariant not found"));
		
		productVariantRepository.delete(productVariant);
		return "success";
	}

	public ProductVariantResponse createProductVariant(ProductVariantCreateRequest request) {
		// TODO Auto-generated method stub
		
		Product product = productRepository.findById(request.getProductId())
	            .orElseThrow(() -> new RuntimeException("Product not found"));
		
		ProductVariant productVariant=productVariantMapper.toEntity(request);

		if(product.getProductVariants().isEmpty()) {
			productVariant.setDefault(true);
		}
		
		
		productVariant.setProduct(product);
		
		
		for(ProductAttribute attribute : productVariant.getAttributes()){
			
			attribute.setProductVariant(productVariant);
		}
			
		ProductVariant savedProductVariant=productVariantRepository.save(productVariant);
		
		return productVariantMapper.toResponse(savedProductVariant);
	}

	public List<ProductVariantResponse> getProductVariants(Long id) {
		// TODO Auto-generated method stub
		Product product=productRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("product not found"));
		
		return product.getProductVariants()
				.stream()
				.map(variant ->{
					ProductVariantResponse response=productVariantMapper.toResponse(variant);
					
					ProductImage primaryImage=variant.getImages()
							.stream()
							.filter(ProductImage::isPrimaryImage)
							.findFirst()
							.orElse(null);
					
					if(primaryImage!=null)
					response.setThumbnail(primaryImage.getImageUrl());
					
					return response;
				})
				.toList();
	}

	public String deleteAllProductVariants(Long id) {
		// TODO Auto-generated method stub
		Product product=productRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("product not found"));
		
		for(ProductVariant variant : product.getProductVariants()) {
			productVariantRepository.delete(variant);
		}
		return "success";
	}

	public List<ProductVariantDetailResponse> getAllProductVariants(Long id) {
		// TODO Auto-generated method stub
		
		Product product=productRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("product not found")); 
		
		List<ProductVariant> variants = product.getProductVariants();
		
		
		
		return variants.stream()
				.map(productVariantMapper::toDetailResponse)
				.toList();
	}

	

}
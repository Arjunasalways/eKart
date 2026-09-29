package com.arjun.ekart.productvariants.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.arjun.ekart.productimages.entity.ProductImage;
import com.arjun.ekart.productimages.service.ProductImageService;
import com.arjun.ekart.productvariants.dto.ProductVariantDetailResponse;
import com.arjun.ekart.productvariants.entity.ProductVariant;
import com.arjun.ekart.productvariants.productvariantmapper.ProductVariantMapper;
import com.arjun.ekart.productvariants.repository.ProductVariantRepository;





@Service
public class VariantService {
	
	@Autowired
	private ProductVariantRepository productVariantRepository;
		
	@Autowired
	private ProductVariantMapper productVariantMapper;
	
	@Autowired
	private ProductImageService productImageService;	

	public String deleteAllVariants() {
		// TODO Auto-generated method stub
		List<ProductVariant> variants=productVariantRepository.findAll();
		
		for(ProductVariant variant:variants) {
						
			for(ProductImage image:variant.getImages()) {
				productImageService.diskImageRemover(image);
			}
			
		}
		productVariantRepository.deleteAll();
		
		return "success";
	}

	public List<ProductVariantDetailResponse> getAllVariants() {
		// TODO Auto-generated method stub
		
		return productVariantRepository.findAll()
				.stream()
				.map(productVariantMapper::toDetailResponse)
				.toList();
	}

}
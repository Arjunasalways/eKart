package com.arjun.ekart.productimages.service;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.arjun.ekart.productimages.dto.ProductImageResponse;
import com.arjun.ekart.productimages.entity.ProductImage;
import com.arjun.ekart.productimages.mapper.ProductImageMapper;
import com.arjun.ekart.productimages.repository.ProductImageRepository;
import com.arjun.ekart.productvariants.entity.ProductVariant;
import com.arjun.ekart.productvariants.repository.ProductVariantRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductImageService {
	
	@Autowired
	private ProductImageRepository productImageRepository;
	
	@Autowired 
	private ProductVariantRepository productVariantRepository;
	
	@Autowired
	private ProductImageMapper productImageMapper;

	@Value("${file.upload-dir}")
	private String uploadDir;
	
	public ProductImageResponse uploadImage(Long variantId, MultipartFile file, boolean primaryImage) {
		// TODO Auto-generated method stub
		
		ProductVariant productVariant = productVariantRepository.findById(variantId)
				.orElseThrow(() -> new RuntimeException("variant not found"));
				
		Path uploadPath=Paths.get(uploadDir);
				
		String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

		Path filePath = uploadPath.resolve(fileName);

		try {
			Files.copy(file.getInputStream(),
			           filePath,
			           StandardCopyOption.REPLACE_EXISTING);
		}catch (IOException e) {
		    throw new RuntimeException("Failed to upload image", e);
		}
		
		ProductImage productImage = new ProductImage();

		productImage.setProductVariant(productVariant);
		productImage.setImageUrl("/images/" + fileName);
		productImage.setPrimaryImage(primaryImage);
		
		ProductImage savedImage= productImageRepository.save(productImage);
		
		return productImageMapper.toResponse(savedImage);
	}

	public List<ProductImageResponse> getImages(Long variantId) {
		// TODO Auto-generated method stub
		ProductVariant productVariant = productVariantRepository.findById(variantId)
				.orElseThrow(() -> new RuntimeException("variant not found"));
		
		
		return productVariant.getImages()
				.stream()
				.map(productImageMapper :: toResponse)
				.toList();
	}

	
	@Transactional
	public String deleteImages(Long variantId) {

	    ProductVariant productVariant = productVariantRepository.findById(variantId)
	            .orElseThrow(() -> new RuntimeException("variant not found"));
	    
	    for(ProductImage image:productVariant.getImages()) {
	    	
	    	diskImageRemover(image);
	    }

	    productVariant.getImages().clear();
	    
	    

	    productVariantRepository.save(productVariant);

	    return "Success";
	}
	
	public String deleteImage(Long variantId, Long imageId) {
		// TODO Auto-generated method stub
		
		ProductVariant productVariant = productVariantRepository.findById(variantId)
				.orElseThrow(() -> new RuntimeException("variant not found"));
		
		ProductImage productImage=productImageRepository.findById(imageId)
				.orElseThrow(() -> new RuntimeException("image not found"));
		
		if(!productImage.getProductVariant().getId().equals(variantId)) {
			throw new RuntimeException("image does not belong to this variant");
		}
		
		diskImageRemover(productImage);


	    // delete database record
	    productImageRepository.delete(productImage);
		
		return "Success";
	}
	
	public ProductImageResponse updateImage(Long variantId, Long imageId,MultipartFile file,boolean primaryImage) {
		
		ProductVariant productVariant = productVariantRepository.findById(variantId)
				.orElseThrow(() -> new RuntimeException("variant not found"));
		
		ProductImage productImage=productImageRepository.findById(imageId)
				.orElseThrow(() -> new RuntimeException("image not found"));
		
		if(!productImage.getProductVariant().getId().equals(variantId)) {
			throw new RuntimeException("image does not belong to this variant");
		}
		
		if(file!=null && !file.isEmpty()) {
		
			diskImageRemover(productImage);
			
			String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
			
			
			Path newFile = Paths.get(uploadDir, fileName);
			
			try {
				
				Files.copy(file.getInputStream(),
			        newFile,
			        StandardCopyOption.REPLACE_EXISTING);
				
			}catch (IOException e) {
				
			    throw new RuntimeException("Failed to upload image", e);
			    
			}
			
			productImage.setImageUrl("/images/" + fileName);
			
		}

		productImage.setPrimaryImage(primaryImage);
		
		ProductImage saved =
		        productImageRepository.save(productImage);

		return productImageMapper.toResponse(saved);
		
		
	}
	
	public void diskImageRemover(ProductImage image) {
		
		String oldPath=Paths.get(image.getImageUrl())
    			.getFileName()
    			.toString();
		
    	try {
    		Files.deleteIfExists(Paths.get(uploadDir,oldPath));
    	} catch(IOException e) {
    		throw new RuntimeException("file doesn't exist");
    	}
    	
	}
	
	
	
}

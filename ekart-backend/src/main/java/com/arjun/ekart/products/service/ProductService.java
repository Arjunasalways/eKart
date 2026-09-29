package com.arjun.ekart.products.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.arjun.ekart.categories.dto.CategoryResponse;
import com.arjun.ekart.categories.entity.Category;
import com.arjun.ekart.categories.repository.CategoryRepository;
import com.arjun.ekart.pagination.dto.PageResponse;
import com.arjun.ekart.pagination.mapper.PageMapper;
import com.arjun.ekart.productimages.entity.ProductImage;
import com.arjun.ekart.products.dto.ProductCreateRequest;
import com.arjun.ekart.products.dto.ProductDetailResponse;
import com.arjun.ekart.products.dto.ProductResponse;
import com.arjun.ekart.products.dto.ProductUpdateRequest;
import com.arjun.ekart.products.entity.Product;
import com.arjun.ekart.products.mapper.ProductMapper;
import com.arjun.ekart.products.repository.ProductRepository;
import com.arjun.ekart.products.specifications.ProductSpecification;
import com.arjun.ekart.productvariants.dto.ProductVariantResponse;
import com.arjun.ekart.productvariants.entity.ProductVariant;
import com.arjun.ekart.productvariants.productvariantmapper.ProductVariantMapper;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired 
	private ProductMapper productMapper;
	
	@Autowired
	private ProductVariantMapper productVariantMapper;
	
	@Autowired
	private PageMapper pageMapper;

	public PageResponse<ProductResponse> getAllProducts(Pageable pageable,
	        String search,
	        List<String> brand,
	        Long categoryId,
	        BigDecimal lower,
	        BigDecimal higher) {
		// TODO Auto-generated method stub
		
		Specification<Product> spec = Specification.unrestricted();
		
		if(search!=null && !search.isBlank()) {
			
			spec=spec.and(ProductSpecification.hasSearch(search));
		
		}
		
		if(brand!=null) {
			
			spec=spec.and(ProductSpecification.hasBrand(brand));
			
		}
		if(categoryId!=null ) {
			
			spec=spec.and(ProductSpecification.hasCategory(categoryId));
			
		}
			
		spec=spec.and(ProductSpecification.hasPrice(lower,higher));
		
		
		
			
		Page<Product> products = productRepository.findAll(spec,pageable);
		
		Page<ProductResponse> response = products.map(product->{
			Optional<ProductVariant> variant= product.getProductVariants()
				.stream()
				.filter(ProductVariant::isDefault)
				.findFirst();
			
			ProductResponse productResponse = productMapper.toResponse(product);
			
			ProductVariantResponse vr=null;
			
			if(variant.isPresent()) {
				
				vr=productVariantMapper.toResponse(variant.get());
				
				ProductImage primaryImage = variant.get().getImages()
						.stream()
						.filter(ProductImage::isPrimaryImage)
						.findFirst()
						.orElse(null);
				
				if (primaryImage != null)
					vr.setThumbnail(primaryImage.getImageUrl());
			}
			
			
			
			productResponse.setDefaultVariant(vr);
			
			return productResponse;
		});
		
		
		return pageMapper.toResponse(response);
	}

	public ProductDetailResponse getProduct(Long id) {
		// TODO Auto-generated method stub	
		Product product=productRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("product not found"));
		
		return productMapper.toDetailResponse(product);
	}

	public ProductResponse updateProduct(Long id, ProductUpdateRequest request) {
		// TODO Auto-generated method stub
		
		Product product=productRepository.findById(id)
				.orElseThrow( ()-> new RuntimeException("product not found") );
		
		productMapper.updateEntity(request,product);
		
		if(request.getCategory()!=null) {
			
			assignCategory(product,request.getCategory());
			
		}
		
		product.setUpdatedAt(LocalDateTime.now());
				
		productRepository.save(product);
		
		return productMapper.toResponse(product);
	}

	public String deleteProduct(Long id) {
		// TODO Auto-generated method stub
		Product product=productRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("product not found"));
		
		productRepository.delete(product);
		return "success";
	}

	public ProductResponse createProduct(ProductCreateRequest request) {
		// TODO Auto-generated method stub
		
		Product product=productMapper.toEntity(request);
		
		assignCategory(product,request.getCategory());
		
		product.setCreatedAt(LocalDateTime.now());
		product.setUpdatedAt(LocalDateTime.now());
			
		Product savedProduct=productRepository.save(product);
		
		return productMapper.toResponse(savedProduct);
	}
	
	private void assignCategory(Product product, Long categoryId) {
	    Category category = categoryRepository.findById(categoryId)
	            .orElseThrow(() -> new RuntimeException("Category not found"));
	    
	    product.setCategory(category);
	}

	public String deleteAllProducts() {
		// TODO Auto-generated method stub
		
		productRepository.deleteAll();
		return "succes";
	}

	public PageResponse<ProductResponse> getAllProductsByCategory(Pageable pageable,Long categoryId) {
		// TODO Auto-generated method stub
		Page<Product> products = productRepository.findByCategoryId(pageable,categoryId);
		
		Page<ProductResponse> response = products.map(product->{
			Optional<ProductVariant> variant= product.getProductVariants()
				.stream()
				.filter(ProductVariant::isDefault)
				.findFirst();
			
			ProductResponse productResponse = productMapper.toResponse(product);
			
			ProductVariantResponse vr=null;
			
			if(variant.isPresent()) {
				
				vr=productVariantMapper.toResponse(variant.get());
				
				ProductImage primaryImage = variant.get().getImages()
						.stream()
						.filter(ProductImage::isPrimaryImage)
						.findFirst()
						.orElse(null);
				
				if (primaryImage != null)
					vr.setThumbnail(primaryImage.getImageUrl());
			}
			
			
			
			productResponse.setDefaultVariant(vr);
			
			return productResponse;
		});
		
		
		return pageMapper.toResponse(response);
		
		
	}
}

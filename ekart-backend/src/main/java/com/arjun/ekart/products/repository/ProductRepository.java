package com.arjun.ekart.products.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.arjun.ekart.products.entity.Product;

public interface ProductRepository extends JpaRepository <Product,Long>,
											JpaSpecificationExecutor<Product>{
	
	Page<Product> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
			String search ,
			String description,
			Pageable pageable);

	Page<Product> findByCategoryId(Pageable pageable,Long categoryId);
	
}

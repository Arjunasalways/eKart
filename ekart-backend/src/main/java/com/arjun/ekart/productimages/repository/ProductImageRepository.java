package com.arjun.ekart.productimages.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arjun.ekart.productimages.entity.ProductImage;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage,Long>{

}

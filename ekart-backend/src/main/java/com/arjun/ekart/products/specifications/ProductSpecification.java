package com.arjun.ekart.products.specifications;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.arjun.ekart.products.entity.Product;

import jakarta.persistence.criteria.CriteriaBuilder;

public class ProductSpecification {
	
	public static Specification<Product> hasCategory(Long categoryId){
		
		return (root,query,cb) ->
		cb.equal(
				root.get("category").get("id"),
				categoryId
				);
	}
	
	public static Specification<Product> hasSearch(String search){
		
		return (root,query,cb) ->{
			
			String pattern = "%" + search.toLowerCase() +"%";
			return cb.or(
					cb.like(
							cb.lower(root.get("name")),
							pattern
							
					),
					cb.like(
							
							cb.lower(root.get("description")),
							pattern
					)
			);
		};
	}
	public static Specification<Product> hasPrice(BigDecimal lower, BigDecimal higher){
		
		return (root,query,cb) ->{
			
			if(lower!=null&&higher!=null) {
						return cb.between(root.get("price"),lower,higher);
						
			}
			
			if(higher!=null) {
				
				return cb.greaterThanOrEqualTo(root.get("price"),lower);
			 }
			
			if(lower!=null) {
						
				return cb.lessThanOrEqualTo(root.get("price"),higher);
			}
			
			return cb.conjunction();
		};
	}
	
	public static Specification<Product> hasBrand(List<String> brands){
			
		return (root,query,cb) ->{
			
			CriteriaBuilder.In<String> inClause = cb.in(cb.lower(root.get("brand")));

			brands.forEach(
					brand->inClause.value(brand.toLowerCase()));
			
			return inClause;
		};
	}
}

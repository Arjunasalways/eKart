package com.arjun.ekart.productvariants.entity;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.arjun.ekart.productattributes.entity.ProductAttribute;
import com.arjun.ekart.productimages.entity.ProductImage;
import com.arjun.ekart.products.entity.Product;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="productvariants")
public class ProductVariant {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false , unique=true , length=100)
	private String sku;
	
	@ManyToOne
	@JoinColumn(name="product_id" , nullable=false)
	private Product product;
	
	@Column(nullable = false , precision=10 , scale=2)
	private BigDecimal price;
	
	@Column(nullable = false )
	private Integer stockQuantity;
	
	@Column
	private LocalDateTime createdAt;
	
	@Column
	private LocalDateTime updatedAt;
	
	@Column
	private boolean active;
	
	@Column(nullable = false)
	private boolean isDefault;
	
	
	
	public boolean isDefault() {
		return isDefault;
	}

	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	@OneToMany(mappedBy="productVariant",
			cascade = CascadeType.ALL,
		    orphanRemoval = true)
	private List<ProductAttribute> attributes;
	
	@OneToMany(mappedBy="productVariant",
			cascade = CascadeType.ALL,
		    orphanRemoval = true)
	private List<ProductImage> images;
	
	
	
	public List<ProductImage> getImages() {
		return images;
	}

	public void setImages(List<ProductImage> images) {
		this.images = images;
	}

	public List<ProductAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<ProductAttribute> attributes) {
		this.attributes = attributes;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(Integer stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

}

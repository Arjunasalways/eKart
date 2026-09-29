package com.arjun.ekart.cartitems.entity;

import com.arjun.ekart.cart.entity.Cart;
import com.arjun.ekart.productvariants.entity.ProductVariant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Min;

@Entity
@Table(
	    name = "cart_items",
	    uniqueConstraints = {
	        @UniqueConstraint(
	            name = "uk_cart_product_variant",
	            columnNames = {"cart_id", "product_variant_id"}
	        )
	    }
	)
public class CartItem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="cart_id",nullable=false)
	private Cart cart;
	
	@Min(1)
	private int quantity;
	
	@ManyToOne
	@JoinColumn(name="product_variant_id"
	,nullable=false)
	private ProductVariant productVariant;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public ProductVariant getProductVariant() {
		return productVariant;
	}

	public void setProductVariant(ProductVariant productVariant) {
		this.productVariant = productVariant;
	}
	
	
	
}

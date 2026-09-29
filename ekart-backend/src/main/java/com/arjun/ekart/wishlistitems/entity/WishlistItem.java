package com.arjun.ekart.wishlistitems.entity;

import com.arjun.ekart.productvariants.entity.ProductVariant;
import com.arjun.ekart.wishlist.entity.Wishlist;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
		uniqueConstraints = @UniqueConstraint(
				columnNames = {"product_variant_id",
						"wishlist_id"}
				)
		)
public class WishlistItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="wishlist_id")
	private Wishlist wishlist;
	
	@OneToOne
	@JoinColumn(name = "product_variant_id")
	private ProductVariant variant;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Wishlist getWishlist() {
		return wishlist;
	}

	public void setWishlist(Wishlist wishlist) {
		this.wishlist = wishlist;
	}

	public ProductVariant getVariant() {
		return variant;
	}

	public void setVariant(ProductVariant variant) {
		this.variant = variant;
	}
	
	
}

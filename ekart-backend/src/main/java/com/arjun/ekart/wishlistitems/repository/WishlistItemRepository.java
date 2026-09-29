package com.arjun.ekart.wishlistitems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arjun.ekart.productvariants.entity.ProductVariant;
import com.arjun.ekart.wishlist.entity.Wishlist;
import com.arjun.ekart.wishlistitems.entity.WishlistItem;

@Repository
public interface WishlistItemRepository extends JpaRepository<WishlistItem, Long>{

	WishlistItem findByWishlistAndVariant(Wishlist wishlist, ProductVariant variant);

}

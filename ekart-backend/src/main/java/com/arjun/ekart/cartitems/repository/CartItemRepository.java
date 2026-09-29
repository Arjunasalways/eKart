package com.arjun.ekart.cartitems.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arjun.ekart.cart.entity.Cart;
import com.arjun.ekart.cartitems.entity.CartItem;
import com.arjun.ekart.productvariants.entity.ProductVariant;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long>{
	
	Optional<CartItem> findByCartAndProductVariant(Cart cart,ProductVariant variant);
	
	Optional<CartItem> findByCart(Cart cart);
}

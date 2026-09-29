package com.arjun.ekart.wishlist.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.arjun.ekart.wishlist.entity.Wishlist;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist,Long>{

	Optional<Wishlist> findByUserEmail(String email);


}

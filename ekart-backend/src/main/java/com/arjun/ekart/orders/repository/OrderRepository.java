package com.arjun.ekart.orders.repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arjun.ekart.orders.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

	Page<Order> findByUserIdOrderByOrderedAtDesc(Long userId,Pageable pageable);
	
	Optional<Order> findByIdAndUserId(Long orderId, Long userId);

	List<Order> findByUserIdOrderByOrderedAtDesc(Long id);

}

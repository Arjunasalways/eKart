package com.arjun.ekart.orderitems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arjun.ekart.orderitems.entity.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Long>{
    List<OrderItem> findByOrderId(Long orderId);

}

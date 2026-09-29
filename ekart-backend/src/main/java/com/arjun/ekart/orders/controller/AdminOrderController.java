package com.arjun.ekart.orders.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arjun.ekart.orders.dto.OrderResponse;
import com.arjun.ekart.orders.service.OrderService;
import com.arjun.ekart.pagination.dto.PageResponse;

@RestController
@RequestMapping("/admin")
public class AdminOrderController {
	
	@Autowired
	private OrderService orderService;

	
	@GetMapping("/orders")
	public PageResponse<OrderResponse> getAllOrders(Pageable pageable){ 
		return orderService.getAllOrders(pageable);
	}
	
	@GetMapping("/users/{userId}/orders")
	public PageResponse<OrderResponse> getUserOrders(@PathVariable Long userId,Pageable pageable){ 
		return orderService.getUserOrders(userId,pageable);
	}
	@GetMapping("/orders/{orderId}")
	public OrderResponse getUserOrder(@PathVariable Long orderId){ 
		return orderService.getUserOrder(orderId);
	}
}

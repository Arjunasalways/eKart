package com.arjun.ekart.orders.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arjun.ekart.orders.dto.BuyNowRequest;
import com.arjun.ekart.orders.dto.CartCheckOutRequest;
import com.arjun.ekart.orders.dto.OrderResponse;
import com.arjun.ekart.orders.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/buy-now")
	public OrderResponse createOrder(@RequestBody @Valid BuyNowRequest request,Authentication auth) {
		return orderService.createOrder(request,auth.getName());
	}
	
	@PostMapping("/check-out")
	public OrderResponse cartCheckOut(@RequestBody @Valid CartCheckOutRequest request,Authentication auth) {
		return orderService.cartCheckout(request,auth.getName());
	}
	
	@GetMapping("/{orderId}")
	private OrderResponse getOrder(@PathVariable Long orderId, Authentication auth) {
		return orderService.getOrder(auth.getName(), orderId);
	}

	@GetMapping
	private List<OrderResponse> getUserOrders(Authentication auth) {
		return orderService.getUserOrders(auth.getName());
	}
	
}

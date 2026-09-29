package com.arjun.ekart.orders.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.arjun.ekart.addresses.dto.AddressResponse;
import com.arjun.ekart.orderitems.dto.OrderItemResponse;
import com.arjun.ekart.orders.enums.OrderStatus;

public class OrderResponse {
	
	private Long id;
	
	private LocalDateTime orderedAt;
	
	private BigDecimal orderPrice;
	
	private List<OrderItemResponse> orderItems;
	
	private AddressResponse deliveryAddress;
	
	private OrderStatus orderStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getOrderedAt() {
		return orderedAt;
	}

	public void setOrderedAt(LocalDateTime orderedAt) {
		this.orderedAt = orderedAt;
	}

	public BigDecimal getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(BigDecimal orderPrice) {
		this.orderPrice = orderPrice;
	}

	public List<OrderItemResponse> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemResponse> orderItems) {
		this.orderItems = orderItems;
	}

	public AddressResponse getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(AddressResponse deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	
}

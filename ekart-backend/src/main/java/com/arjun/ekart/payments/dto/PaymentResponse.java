package com.arjun.ekart.payments.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.arjun.ekart.payments.enums.PaymentStatus;


import jakarta.validation.constraints.Min;

public class PaymentResponse {
	
	private Long id;
	
	private Long orderId;
	
	private LocalDateTime createdAt;
	
	@Min(1)
	private BigDecimal amount;
	
	private String paymentMethod;
	
	private PaymentStatus paymentStatus;
	
	private String razprpayPaymentId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getRazprpayPaymentId() {
		return razprpayPaymentId;
	}

	public void setRazprpayPaymentId(String razprpayPaymentId) {
		this.razprpayPaymentId = razprpayPaymentId;
	}
	
	
}

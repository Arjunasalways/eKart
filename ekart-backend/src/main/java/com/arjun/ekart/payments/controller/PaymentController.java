package com.arjun.ekart.payments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arjun.ekart.payments.dto.CreatePaymentRequest;
import com.arjun.ekart.payments.dto.PaymentResponse;
import com.arjun.ekart.payments.dto.RazorpayPaymentDetails;
import com.arjun.ekart.payments.dto.VerifyPaymentRequest;
import com.arjun.ekart.payments.service.PaymentService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/users/payments")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@PostMapping
	public RazorpayPaymentDetails createPayment(@Valid @RequestBody CreatePaymentRequest request,Authentication auth) {
		return paymentService.createPayment(request,auth.getName());
	}
	
	@PostMapping("/verify")
	public String verifyPayment(@Valid @RequestBody VerifyPaymentRequest request,Authentication auth) {
		return paymentService.verifyPayment(request,auth.getName());
	}
	
	@GetMapping("/{paymentId}")
	public PaymentResponse getPayment( @PathVariable Long paymentId, Authentication auth) {
		return paymentService.getPayment(paymentId,auth.getName());
	}
	
	
}

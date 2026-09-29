package com.arjun.ekart.payments.service;

import java.math.BigDecimal;

import java.time.LocalDateTime;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arjun.ekart.orders.entity.Order;
import com.arjun.ekart.orders.enums.OrderStatus;
import com.arjun.ekart.orders.repository.OrderRepository;
import com.arjun.ekart.payments.config.RazorpayConfig;
import com.arjun.ekart.payments.dto.CreatePaymentRequest;
import com.arjun.ekart.payments.dto.PaymentResponse;
import com.arjun.ekart.payments.dto.RazorpayPaymentDetails;
import com.arjun.ekart.payments.dto.VerifyPaymentRequest;
import com.arjun.ekart.payments.entity.Payment;
import com.arjun.ekart.payments.enums.PaymentStatus;
import com.arjun.ekart.payments.mapper.PaymentMapper;
import com.arjun.ekart.payments.repository.PaymentRepository;
import com.arjun.ekart.users.entity.User;
import com.arjun.ekart.users.repository.UserRepository;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;



@Service
public class PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private PaymentMapper paymentMapper;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private RazorpayClient razorpayClient;
	
	@Autowired
	private RazorpayConfig razorpayConfig;
	
	@Autowired
	private UserRepository userRepository;
	

	
	public RazorpayPaymentDetails createPayment(CreatePaymentRequest request,String email) {
		// TODO Auto-generated method stub
		
		Order order = orderRepository.findById(request.getOrderId())
				.orElseThrow(()-> new RuntimeException("order not found"));
		
		User user = userRepository.findByEmail(email)
				.orElseThrow(()->new RuntimeException("user not found"));
		
		if(!(order.getUser().getId().equals(user.getId()))){
			throw new RuntimeException("order doesn't belong to the user");
		}
		
		JSONObject options = new JSONObject();
		
		options.put("amount",
		        order.getOrderPrice()
		             .multiply(BigDecimal.valueOf(100))
		             .longValue());

		options.put("currency", "INR");

		options.put("receipt", "ORDER_" + order.getId());
		
		com.razorpay.Order razorpayOrder;
		
		try {
			razorpayOrder = razorpayClient.orders.create(options);
		} catch (RazorpayException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Unable to create Razorpay order", e);
		}
		
		Payment payment = new Payment();
		
		payment.setAmount(order.getOrderPrice());
		payment.setOrder(order);
		payment.setPaymentStatus(PaymentStatus.PENDING);
		payment.setCreatedAt(LocalDateTime.now());
		payment.setRazorpayOrderId(razorpayOrder.get("id").toString());

		paymentRepository.save(payment);
		
		RazorpayPaymentDetails details = new RazorpayPaymentDetails();
		
		details.setAmount(order.getOrderPrice());
		details.setCurrency("INR");
		details.setKey(razorpayConfig.getKeyId());
		details.setRazorpayOrderId(razorpayOrder.get("id").toString());
		
		return details;
	}

	public String verifyPayment(VerifyPaymentRequest request,String email) {
		// TODO Auto-generated method stub
		
		
		Payment payment = paymentRepository
		        .findByRazorpayOrderId(
		            request.getRazorpayOrderId()
		        )
		        .orElseThrow(() -> 
		            new RuntimeException("Payment not found")
		        );
		
		User user = userRepository.findByEmail(email)
		        .orElseThrow(()-> new RuntimeException("user not found"));

		if (!payment.getOrder().getUser().getId().equals(user.getId())) {
		    throw new RuntimeException("Access denied");
		}
		
		if(payment.getPaymentStatus() == PaymentStatus.SUCCESS) {
		    return "Payment already verified";
		}
		
		String payload = request.getRazorpayOrderId()
		        + "|"
		        + request.getRazorpayPaymentId();


		try {
				Boolean isValid = Utils.verifySignature(
		        payload,
		        request.getRazorpaySignature(),
		        razorpayConfig.getKeySecret()
		);
				 if(!isValid) {
				        throw new RuntimeException("Invalid payment signature");
				    }
		}catch(RazorpayException e) {
		    throw new RuntimeException("Payment verification failed", e);
		}
		
		Order order = payment.getOrder();

		payment.setRazorpayPaymentId(
		        request.getRazorpayPaymentId()
		);

		payment.setPaymentStatus(PaymentStatus.SUCCESS);

		payment.setPaidAt(LocalDateTime.now());

		paymentRepository.save(payment);
		
		order.setOrderStatus(OrderStatus.CONFIRMED);

		orderRepository.save(order);

		return "success";
	}

	public PaymentResponse getPayment(Long paymentId,String email) {
		// TODO Auto-generated method stub
		
		Payment payment = paymentRepository
		        .findById(paymentId)
		        .orElseThrow(() -> 
		            new RuntimeException("Payment not found")
		        );
		
		User user = userRepository.findByEmail(email)
				.orElseThrow(()-> new RuntimeException("user not found"));
		
		if (!payment.getOrder().getUser().getId().equals(user.getId())) {
		    throw new RuntimeException("Access denied");
		}
		
		return paymentMapper.toResponse(payment);
	}

}

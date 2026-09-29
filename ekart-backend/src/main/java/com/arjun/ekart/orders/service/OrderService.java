package com.arjun.ekart.orders.service;

import java.math.BigDecimal;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.arjun.ekart.addresses.entity.Address;
import com.arjun.ekart.addresses.repository.AddressRepository;
import com.arjun.ekart.cart.entity.Cart;
import com.arjun.ekart.cart.repository.CartRepository;
import com.arjun.ekart.cartitems.entity.CartItem;
import com.arjun.ekart.orderitems.entity.OrderItem;
import com.arjun.ekart.orders.dto.BuyNowRequest;
import com.arjun.ekart.orders.dto.CartCheckOutRequest;
import com.arjun.ekart.orders.dto.OrderResponse;
import com.arjun.ekart.orders.entity.Order;
import com.arjun.ekart.orders.enums.OrderStatus;
import com.arjun.ekart.orders.mapper.OrderMapper;
import com.arjun.ekart.orders.repository.OrderRepository;
import com.arjun.ekart.pagination.dto.PageResponse;
import com.arjun.ekart.pagination.mapper.PageMapper;
import com.arjun.ekart.productvariants.entity.ProductVariant;
import com.arjun.ekart.productvariants.repository.ProductVariantRepository;
import com.arjun.ekart.users.entity.User;
import com.arjun.ekart.users.repository.UserRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private ProductVariantRepository variantRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private PageMapper pageMapper;
	


	@Autowired
	private CartRepository cartRepository;

	public List<OrderResponse> getUserOrders(String email) {

		
		User user=userRepository.findByEmail(email)
				.orElseThrow(()-> new RuntimeException("user not found"));
		

	    return orderRepository.findByUserIdOrderByOrderedAtDesc(user.getId())
	            .stream()
	            .map(orderMapper::toResponse)
	            .toList();
	}
	
	@Transactional
	public OrderResponse createOrder(BuyNowRequest request,String email) {
		// TODO Auto-generated method stub
		
		ProductVariant variant = variantRepository.findById(request.getVariantId())
				.orElseThrow(()->  new RuntimeException("variant doesn't exist"));

		if(request.getQuantity() > variant.getStockQuantity()){
			throw new RuntimeException("Insufficient stock");
		}
		
		User user=userRepository.findByEmail(email)
				.orElseThrow(()-> new RuntimeException("user not found"));
		
		Order order = orderMapper.toEntity(request);
		
		OrderItem orderItem = new OrderItem();
		
		Address address = addressRepository.findById(request.getAddressId())
		        .orElseThrow(() -> new RuntimeException("Address not found"));

		if (!address.getUser().getId().equals(user.getId())) {
		    throw new RuntimeException("Address doesn't belong to this user");
		}
		
		BigDecimal total = variant.getPrice()
		        .multiply(BigDecimal.valueOf(request.getQuantity()));
		
		Integer quantity = request.getQuantity();
		
		
		buildOrderItem(orderItem,order,total,variant,quantity);

		buildOrder(order,List.of(orderItem),address,total,user);
		
		
		
		
		variant.setStockQuantity(variant.getStockQuantity()-quantity);
		
		
		orderRepository.save(order);
		
		return orderMapper.toResponse(order);
	}

	

	public OrderResponse getOrder(String email,Long orderId) {
		// TODO Auto-generated method stub
		User user=userRepository.findByEmail(email)
				.orElseThrow(()-> new RuntimeException("user not found"));
		
		Order order = orderRepository.findByIdAndUserId(orderId,user.getId())
				.orElseThrow(()-> new RuntimeException("order not found"));
		
		return orderMapper.toResponse(order);
	}

	@Transactional
	public OrderResponse cartCheckout(@Valid CartCheckOutRequest request,String email) {
		// TODO Auto-generated method stub
		User user=userRepository.findByEmail(email)
				.orElseThrow(()-> new RuntimeException("user not found"));
		
		Address address = addressRepository.findById(request.getAddressId())
		        .orElseThrow(() -> new RuntimeException("Address not found"));
		
		if (!address.getUser().getId().equals(user.getId())) {
			throw new RuntimeException("Address doesn't belong to this user");
		}
		
		Cart cart = cartRepository.findById(user.getCart().getId())
				.orElseThrow(()-> new RuntimeException("cart not found"));

		
		Order order = new Order();
		
		List<OrderItem> orderItems = new ArrayList<>();
		
		BigDecimal totalAmount = BigDecimal.ZERO;
		
		if (cart.getCartItems().isEmpty()) {
		    throw new RuntimeException("Cart is empty");
		}
		
		for(CartItem cartItem:cart.getCartItems()) {
			
			OrderItem orderItem = new OrderItem();
			
			ProductVariant variant = cartItem.getProductVariant();
			
			if(cartItem.getQuantity() > variant.getStockQuantity()){
				throw new RuntimeException("Insufficient stock");
			}
			
			BigDecimal total = variant.getPrice()
			        .multiply(BigDecimal.valueOf(cartItem.getQuantity()));
			
			Integer quantity = cartItem.getQuantity();
			
			buildOrderItem(orderItem,order,total,variant,quantity);

			
			variant.setStockQuantity(variant.getStockQuantity()-quantity);
			
			orderItems.add(orderItem);
			
			totalAmount=totalAmount.add(total);
			
		}
		
		cart.getCartItems().clear();
		
		buildOrder(order,orderItems,address,totalAmount,user);	
		
		orderRepository.save(order);
		
		return orderMapper.toResponse(order);
	}
	
	private void buildOrderItem(OrderItem orderItem, Order order, BigDecimal total, ProductVariant variant,Integer quantity) {
		// TODO Auto-generated method stub
		orderItem.setOrder(order);
		
		orderItem.setPrice(variant.getPrice());
		
		orderItem.setProductName(variant.getProduct().getName());
		
		orderItem.setQuantity(quantity);
		
		orderItem.setSku(variant.getSku());
		
		orderItem.setVariantDescription(
				variant.getAttributes().stream()
				.map(attribute->attribute.getAttributeName()+":"+attribute.getAttributeValue())
				.collect(Collectors.joining(",")));
		
		orderItem.setSubTotal(total);
		
		orderItem.setVariant(variant);
		
	}

	private void buildOrder(Order order, List<OrderItem> orderItems, Address address, BigDecimal total, User user) {
		// TODO Auto-generated method stub
		
		order.setDeliveryAddress(address);
		
		order.setOrderedAt(LocalDateTime.now());
		
		order.setOrderPrice(total);
		
		order.setOrderStatus(OrderStatus.PLACED);
		
		order.setUser(user);
		
		order.setOrderItems(orderItems);

		
	}

	public PageResponse<OrderResponse> getAllOrders(Pageable pageable) {
		// TODO Auto-generated method stub
		Page<Order> orders = orderRepository.findAll(pageable);
		
		Page<OrderResponse> response = orders
				.map(orderMapper::toResponse);
		
		return pageMapper.toResponse(response);
	}

	public PageResponse<OrderResponse> getUserOrders(Long userId,Pageable pageable) {
		// TODO Auto-generated method stub
		
		userRepository.findById(userId)
	    .orElseThrow(() -> new RuntimeException("User not found"));
		
		Page<Order> orders =  orderRepository.findByUserIdOrderByOrderedAtDesc(userId,pageable);
		
		Page<OrderResponse> response = orders
				.map(orderMapper::toResponse);
		
		return pageMapper.toResponse(response);
	}

	public OrderResponse getUserOrder(Long orderId) {
		// TODO Auto-generated method stub
		Order order = orderRepository.findById(orderId)
				.orElseThrow(()->new RuntimeException("order not found"));
		
		return orderMapper.toResponse(order);
	}

	

}

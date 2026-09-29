package com.arjun.ekart.orders.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.arjun.ekart.addresses.mapper.AddressMapper;
import com.arjun.ekart.orderitems.mapper.OrderItemMapper;
import com.arjun.ekart.orders.dto.BuyNowRequest;
import com.arjun.ekart.orders.dto.OrderResponse;
import com.arjun.ekart.orders.entity.Order;

@Mapper(componentModel="spring",uses= {
				OrderItemMapper.class,
				AddressMapper.class
})
public interface OrderMapper {
	
	OrderResponse toResponse(Order order);
	
	@Mapping(target="orderedAt" ,ignore =true)
	@Mapping(target="orderPrice" ,ignore =true)
	@Mapping(target="user" ,ignore =true)
	@Mapping(target="orderItems" ,ignore =true)
	@Mapping(target="deliveryAddress" ,ignore =true)
	@Mapping(target="orderStatus" ,ignore =true)
	Order toEntity(BuyNowRequest request);
		
	
}

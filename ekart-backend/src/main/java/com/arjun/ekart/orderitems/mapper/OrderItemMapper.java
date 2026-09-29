package com.arjun.ekart.orderitems.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.arjun.ekart.orderitems.dto.OrderItemCreateRequest;
import com.arjun.ekart.orderitems.dto.OrderItemResponse;
import com.arjun.ekart.orderitems.entity.OrderItem;

@Mapper(componentModel="spring")
public interface OrderItemMapper {
	
	@Mapping(target="thumbnail",ignore = true)
	@Mapping(target="variantId",source = "variant.id")
	OrderItemResponse toResponse(OrderItem orderItem);
	
	
	@Mapping(target="price",ignore = true)
	@Mapping(target="subTotal",ignore = true)
	@Mapping(target="productName",ignore = true)
	@Mapping(target="sku",ignore = true)
	@Mapping(target="variant",ignore = true)
	@Mapping(target="order",ignore = true)
	OrderItem toEntity(OrderItemCreateRequest request);
}

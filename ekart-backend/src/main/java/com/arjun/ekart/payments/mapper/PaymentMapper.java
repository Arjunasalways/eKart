package com.arjun.ekart.payments.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.arjun.ekart.payments.dto.PaymentResponse;
import com.arjun.ekart.payments.entity.Payment;



@Mapper(componentModel="spring")
public interface PaymentMapper {
	
	@Mapping(source="order.id" ,target="orderId")
	PaymentResponse toResponse(Payment payment);
	
}

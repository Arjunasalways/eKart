package com.arjun.ekart.orderitems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arjun.ekart.orderitems.dto.OrderItemResponse;
import com.arjun.ekart.orderitems.entity.OrderItem;
import com.arjun.ekart.orderitems.mapper.OrderItemMapper;
import com.arjun.ekart.orderitems.repository.OrderItemRepository;
import com.arjun.ekart.productimages.entity.ProductImage;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderItemMapper orderItemMapper;

    public List<OrderItemResponse> getOrderItems(Long orderId) {

        return orderItemRepository.findByOrderId(orderId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private OrderItemResponse toResponse(OrderItem orderItem) {

        OrderItemResponse response = orderItemMapper.toResponse(orderItem);

        response.setThumbnail(
                orderItem.getVariant()
                        .getImages()
                        .stream()
                        .filter(ProductImage::isPrimaryImage)
                        .findFirst()
                        .map(ProductImage::getImageUrl)
                        .orElse(null)
        );

        return response;
    }
}
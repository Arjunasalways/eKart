package com.arjun.ekart.orderitems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arjun.ekart.orderitems.dto.OrderItemResponse;
import com.arjun.ekart.orderitems.service.OrderItemService;

@RestController
@RequestMapping("/orders")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping("/{orderId}/items")
    public List<OrderItemResponse> getOrderItems(@PathVariable Long orderId) {

        return orderItemService.getOrderItems(orderId);
    }
}
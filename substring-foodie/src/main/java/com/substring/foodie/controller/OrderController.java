package com.substring.foodie.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.substring.foodie.dto.OrderDto;
import com.substring.foodie.dto.OrderPlaceRequest;
import com.substring.foodie.service.OrderService;


@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    private ResponseEntity<OrderDto> placeOrder(@RequestBody OrderPlaceRequest orderPlaceRequest)
            {
        System.out.println("orderPlaceRequest: " + orderPlaceRequest);
        OrderDto orderDto = orderService.placeOrder(orderPlaceRequest);
        return ResponseEntity.ok(orderDto);
    }

}

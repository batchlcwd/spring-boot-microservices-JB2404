package com.service.order.controller;

import com.service.order.dto.OrderCreateRequestDto;
import com.service.order.entity.Order;
import com.service.order.service.OrderService;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    //creating order
    public ResponseEntity<Order> createOrder(
            @RequestBody OrderCreateRequestDto orderCreateRequestDto
    ) {

        Order order = orderService.createOrder(orderCreateRequestDto);
        return new ResponseEntity<>(order, HttpStatus.CREATED);

    }

}

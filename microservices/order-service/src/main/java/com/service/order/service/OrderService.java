package com.service.order.service;

import com.service.order.dto.OrderCreateRequestDto;
import com.service.order.dto.OrderDto;
import com.service.order.entity.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private StreamBridge streamBridge;

    //    OrderReponsitory
    public Order createOrder(OrderCreateRequestDto dto) {

        //

        Order order = new Order();
        order.setOrderId(UUID.randomUUID().toString());
        order.setAmount(dto.getAmount());
        order.setStatus("CREATED");
        order.setUserId(dto.getUserId());
        order.setProductId(dto.getProductId());
        order.setOrderDate(LocalDate.now());
        System.out.println("Order created " + order.getOrderId());

        //TODO:
        //jpa configure
        //save the order to database
        // Publish the event:
        OrderDto orderDto = new OrderDto(order.getOrderId(), Integer.parseInt(order.getAmount()), order.getStatus());
        Message<OrderDto> message = MessageBuilder.withPayload(orderDto).build();
        streamBridge.send("orderCreated-out-0", message);
        System.out.println("Order Event published");
        return order;

    }

}

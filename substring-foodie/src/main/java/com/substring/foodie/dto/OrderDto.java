package com.substring.foodie.dto;

import com.substring.foodie.entity.Address;
import com.substring.foodie.entity.OrderItem;
import com.substring.foodie.entity.Restaurant;
import com.substring.foodie.entity.User;
import com.substring.foodie.entity.enums.OrderStatus;
import com.substring.foodie.entity.enums.PaymentMode;
import com.substring.foodie.entity.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {


    private String id;
    private UserDto user;
    private RestaurantDto restaurant;
    private AddressDto address;
    private int totalAmount;
    private OrderStatus status = OrderStatus.PLACED;
    private LocalDateTime orderedAt;
    private LocalDateTime deliveryTime;
    private UserDto deliveryBoy;
    private List<OrderItemDto> orderItems = new ArrayList<>();
    private PaymentStatus paymentStatus;
    private PaymentMode paymentMode;
    private String paymentId;
    private  String razorpayOrderId;
}

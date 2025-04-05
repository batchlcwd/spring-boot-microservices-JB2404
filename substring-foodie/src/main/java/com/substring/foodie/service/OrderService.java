package com.substring.foodie.service;

import com.substring.foodie.dto.OrderDto;
import com.substring.foodie.dto.OrderItemDto;
import com.substring.foodie.dto.OrderPlaceRequest;
import com.substring.foodie.entity.enums.OrderStatus;

import java.util.List;

public interface OrderService {

    OrderDto placeOrder(OrderPlaceRequest orderPlaceRequest);

    List<OrderDto> getOrders();

    List<OrderDto> getOrderByRestaurant(String restaurantId);

    List<OrderDto> getOrderByUser(String userId);

    List<OrderDto> getOrderByDeliveryBoy(String deliveryBoyId);

    OrderDto tractOrder(String orderId);

    OrderItemDto cancelOrder(String orderId);

    OrderDto updateOrderStatus(OrderStatus orderStatus);

    OrderDto updateOrderStataus(OrderStatus orderStatus);

}

package com.substring.foodie.service.impl;

import com.substring.foodie.dto.OrderDto;
import com.substring.foodie.dto.OrderItemDto;
import com.substring.foodie.dto.OrderPlaceRequest;
import com.substring.foodie.entity.Order;
import com.substring.foodie.entity.OrderItem;
import com.substring.foodie.entity.enums.OrderStatus;
import com.substring.foodie.repository.OrderItemRepository;
import com.substring.foodie.repository.OrderRepository;
import com.substring.foodie.service.OrderService;
import com.substring.foodie.entity.CartItem;
import com.substring.foodie.service.CartService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartService cartService;
    private final ModelMapper mapper;

    @Override
    public OrderDto placeOrder(OrderPlaceRequest orderPlaceRequest) {
        // First save the order
        Order order = mapper.map(orderPlaceRequest, Order.class);
        Order savedOrder = orderRepository.save(order);

        // Get cart items for the user
        List<CartItem> cartItems = cartService.getCartItems(orderPlaceRequest.getUserId());

        // Convert cart items to order items
        List<OrderItem> orderItems = cartItems.stream()
                .map(cartItem -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrder(savedOrder);
                    orderItem.setQuantity(cartItem.getQuantity());
                    orderItem.setFoodItem(cartItem.getFoodItem());
                    return orderItem;
                })
                .collect(Collectors.toList());

        // Save order items
        orderItemRepository.saveAll(orderItems);

        // Clear user's cart
        cartService.clearCart(orderPlaceRequest.getUserId());

        return mapper.map(savedOrder, OrderDto.class);
    }

    @Override
    public List<OrderDto> getOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(order -> mapper.map(order, OrderDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getOrderByRestaurant(String restaurantId) {
        List<Order> orders = orderRepository.findByRestaurantId(restaurantId);
        return orders.stream()
                .map(order -> mapper.map(order, OrderDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getOrderByUser(String userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        return orders.stream()
                .map(order -> mapper.map(order, OrderDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getOrderByDeliveryBoy(String deliveryBoyId) {
        List<Order> orders = orderRepository.findByDeliveryBoyId(deliveryBoyId);
        return orders.stream()
                .map(order -> mapper.map(order, OrderDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto tractOrder(String orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return mapper.map(order, OrderDto.class);
    }

    @Override
    public OrderItemDto cancelOrder(String orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(OrderStatus.CANCELLED);
        Order savedOrder = orderRepository.save(order);
        return mapper.map(savedOrder, OrderItemDto.class);
    }

    @Override
    public OrderDto updateOrderStatus(OrderStatus orderStatus) {
        // This method seems redundant with updateOrderStataus
        throw new UnsupportedOperationException("Use updateOrderStataus instead");
    }

    @Override
    public OrderDto updateOrderStataus(OrderStatus orderStatus) {
        // Note: This method needs more context - like which order to update
        // Assuming it needs orderId parameter
        throw new UnsupportedOperationException("Method needs orderId parameter");
    }
}

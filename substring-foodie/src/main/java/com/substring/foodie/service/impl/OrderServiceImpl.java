package com.substring.foodie.service.impl;

import com.substring.foodie.dto.OrderDto;
import com.substring.foodie.dto.OrderItemDto;
import com.substring.foodie.dto.OrderPlaceRequest;
import com.substring.foodie.entity.Order;
import com.substring.foodie.entity.OrderItem;
import com.substring.foodie.entity.Restaurant;
import com.substring.foodie.entity.User;
import com.substring.foodie.entity.enums.OrderStatus;
import com.substring.foodie.entity.enums.PaymentStatus;
import com.substring.foodie.exception.ResourceNotFoundException;
import com.substring.foodie.repository.CartRepository;
import com.substring.foodie.repository.OrderItemRepository;
import com.substring.foodie.repository.OrderRepository;
import com.substring.foodie.repository.RestaurantRepository;
import com.substring.foodie.repository.UserRepository;
import com.substring.foodie.service.OrderService;
import com.substring.foodie.utils.Helper;

import lombok.AllArgsConstructor;

import com.substring.foodie.entity.Address;
import com.substring.foodie.entity.Cart;
import com.substring.foodie.entity.CartItem;
import com.substring.foodie.service.CartService;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final ModelMapper mapper;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final RestaurantRepository restaurantRepository;

    @Override
    public OrderDto placeOrder(OrderPlaceRequest orderPlaceRequest) {

        User user = userRepository.findById(orderPlaceRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Cart cart = cartRepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        Restaurant restaurant = restaurantRepository.findById(orderPlaceRequest.getRestaurantId())
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));

        List<CartItem> cartItems = cart.getItems();
        System.out.println("size of the cart: " + cartItems.size());
        if (cartItems.isEmpty()) {
            throw new ResourceNotFoundException(
                    "Cart is empty");
        }
        // Convert cart items to order items
        Order order = new Order();
        order.setUser(user);
        order.setRestaurant(restaurant);
        order.setId(Helper.generateRandomId());
        Address address = mapper.map(orderPlaceRequest.getAddress(), Address.class);
        if (address.getId() == null)
            address.setId(null);


        order.setAddress(address);
        order.setStatus(OrderStatus.PLACED);
        order.setOrderedAt(LocalDateTime.now());
        order.setPaymentStatus(
                PaymentStatus.NOT_PAID);
        order.setPaymentMode(
                orderPlaceRequest.getPaymentMode());

        final AtomicInteger totalAmount = new AtomicInteger(0);
        List<OrderItem> orderItems = cartItems.stream()
                .map(cartItem -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrder(order);
                    orderItem.setQuantity(cartItem.getQuantity());
                    orderItem.setFoodItem(cartItem.getFoodItem());
                    totalAmount.set(totalAmount.get()
                            +( (int)( cartItem.getFoodItem().actualPrice() * cartItem.getQuantity())));

                    return orderItem;
                })
                .collect(Collectors.toList());
        order.setTotalAmount(totalAmount.get());
        order.setOrderItems(orderItems);
        orderRepository.save(order);
        // Clear user's cart
        cartService.clearCart(orderPlaceRequest.getUserId());
        return mapper.map(order, OrderDto.class);
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
    public OrderDto cancelOrder(String orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(OrderStatus.CANCELLED);
        Order savedOrder = orderRepository.save(order);
        return mapper.map(savedOrder, OrderDto.class);
    }

    @Override
    public OrderDto updateOrderStataus(String orderId, OrderStatus orderStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + orderId));
        order.setStatus(orderStatus);
        Order savedOrder = orderRepository.save(order);
        return mapper.map(savedOrder, OrderDto.class);
    }
}

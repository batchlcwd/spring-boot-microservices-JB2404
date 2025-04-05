package com.substring.foodie.entity;

import com.substring.foodie.entity.enums.OrderStatus;
import com.substring.foodie.entity.enums.PaymentMode;
import com.substring.foodie.entity.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "food_order")
public class Order {

    @Id
    private String id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
    private int totalAmount;
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PLACED;
    private LocalDateTime orderedAt;
    private LocalDateTime deliveryTime;
    @ManyToOne
    @JoinColumn(name = "delivery_boy_id")
    private User deliveryBoy;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;
    private String paymentId;

}

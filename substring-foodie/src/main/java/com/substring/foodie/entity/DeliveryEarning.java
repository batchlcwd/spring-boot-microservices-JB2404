package com.substring.foodie.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class DeliveryEarning {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "delivery_boy_id")
    private User deliveryBoy;
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;
    private int amount;
    private LocalDateTime deliveryTime;

}



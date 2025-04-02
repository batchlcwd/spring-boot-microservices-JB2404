package com.substring.foodie.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryEarningDto {
    private Long id;
    private OrderDto order;
    private int amount;
    private LocalDateTime deliveryTime;
}

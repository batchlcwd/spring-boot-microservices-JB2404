package com.substring.foodie.dto;

import com.substring.foodie.entity.enums.OrderStatus;
import com.substring.foodie.entity.enums.PaymentMode;
import com.substring.foodie.entity.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderPlaceRequest {
    private String userId;
    private AddressDto address;
    private RestaurantDto restaurantId;
    private OrderStatus status = OrderStatus.PLACED;
    private LocalDateTime orderedAt = LocalDateTime.now();
    private PaymentStatus paymentStatus = PaymentStatus.NOT_PAID;
    private PaymentMode paymentMode;
}

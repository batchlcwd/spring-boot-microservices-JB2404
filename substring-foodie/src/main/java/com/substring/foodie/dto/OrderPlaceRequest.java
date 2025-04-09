package com.substring.foodie.dto;

import com.substring.foodie.entity.enums.OrderStatus;
import com.substring.foodie.entity.enums.PaymentMode;
import com.substring.foodie.entity.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
public class OrderPlaceRequest
{
    private String userId;
    private String restaurantId;
    private AddressDto address;
    private OrderStatus status = OrderStatus.PLACED;
    private LocalDateTime orderedAt = LocalDateTime.now();
    private PaymentStatus paymentStatus = PaymentStatus.NOT_PAID;
    private PaymentMode paymentMode;
    private  String aboutThisOrder;

}

package com.substring.foodie.payment.functions;

public record OrderDto(
        String orderId,
        int amount,
        String status
) {
}

package com.service.order.dto;

public record OrderDto(
        String orderId,
        int amount,
        String status
) {
}

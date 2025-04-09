package com.substring.foodie.playload;

public record PaymentVerifyDto(
        String razorpayOrderId,
        String razorpayPaymentId,
        String razorpaySignature
) {
}

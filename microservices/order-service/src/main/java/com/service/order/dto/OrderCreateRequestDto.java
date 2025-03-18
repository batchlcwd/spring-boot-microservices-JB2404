package com.service.order.dto;

public class OrderCreateRequestDto {

    String orderId;
    String amount;
    String productId;
    String status;

    String userId;

    public OrderCreateRequestDto() {
    }

    public OrderCreateRequestDto(String orderId, String amount, String productId, String status) {
        this.orderId = orderId;
        this.amount = amount;
        this.productId = productId;
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

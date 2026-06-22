package com.CounterX.dto;

import com.CounterX.entity.OrderStatus;
import com.CounterX.entity.OrderType;
import com.CounterX.entity.PaymentStatus;

import java.time.LocalDateTime;

public class AdminOrderDTO {

    private Long orderId;

    //private Integer dailyOrderNumber;

    private OrderType orderType;

    private OrderStatus orderStatus;

    private Double totalAmount;

    private LocalDateTime orderDateTime;

    private PaymentStatus paymentStatus;

    public AdminOrderDTO() {
    }

    public AdminOrderDTO(
            Long orderId,
           // Integer dailyOrderNumber,
            OrderType orderType,
            OrderStatus orderStatus,
            Double totalAmount,
            LocalDateTime orderDateTime,
            PaymentStatus paymentStatus) {

        this.orderId = orderId;
        //this.dailyOrderNumber = dailyOrderNumber;
        this.orderType = orderType;
        this.orderStatus = orderStatus;
        this.totalAmount = totalAmount;
        this.orderDateTime = orderDateTime;
        this.paymentStatus = paymentStatus;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

//    public Integer getDailyOrderNumber() {
//        return dailyOrderNumber;
//    }

//    public void setDailyOrderNumber(Integer dailyOrderNumber) {
//        this.dailyOrderNumber = dailyOrderNumber;
//    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
package com.CounterX.entity;

public enum PaymentStatus {

    // Payment initiated but not completed
    PENDING,

    // Payment completed successfully
    SUCCESS,
    // Payment marked as paid (legacy name)
    PAID,

    // Payment failed
    FAILED,

    // Payment cancelled by customer
    CANCELLED,

    // Payment refunded (optional)
    REFUNDED

}
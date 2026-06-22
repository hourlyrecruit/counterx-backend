
package com.CounterX.dto;

import com.CounterX.entity.OrderStatus;
import com.CounterX.entity.OrderType;

import java.time.LocalDateTime;
import java.util.List;

public class KitchenOrderDTO {

    private Long orderId;
    //private Integer dailyOrderNumber;
    private OrderType orderType;
    private OrderStatus orderStatus;
    private Double totalAmount;
    private LocalDateTime orderDateTime;
    private List<KitchenItemDTO> items;

    public KitchenOrderDTO() {
    }

    public KitchenOrderDTO(Long orderId,
                           //Integer dailyOrderNumber,
                           OrderType orderType,
                           OrderStatus orderStatus,
                           Double totalAmount,
                           LocalDateTime orderDateTime,
                           List<KitchenItemDTO> items) {

        this.orderId = orderId;
        //this.dailyOrderNumber = dailyOrderNumber;
        this.orderType = orderType;
        this.orderStatus = orderStatus;
        this.totalAmount = totalAmount;
        this.orderDateTime = orderDateTime;
        this.items = items;
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

    public List<KitchenItemDTO> getItems() {
        return items;
    }

    public void setItems(List<KitchenItemDTO> items) {
        this.items = items;
    }

    // ── Nested DTO for each item in a kitchen order ──
    public static class KitchenItemDTO {
        private String name;
        private Integer qty;

        public KitchenItemDTO() {
        }

        public KitchenItemDTO(String name, Integer qty) {
            this.name = name;
            this.qty = qty;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getQty() {
            return qty;
        }

        public void setQty(Integer qty) {
            this.qty = qty;
        }
    }
}

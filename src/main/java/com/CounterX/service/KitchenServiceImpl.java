package com.CounterX.service;

import com.CounterX.dto.KitchenOrderDTO;
import com.CounterX.entity.Order;
import com.CounterX.entity.OrderItem;
import com.CounterX.entity.OrderStatus;
import com.CounterX.exception.ResourceNotFoundException;
import com.CounterX.repository.OrderItemRepository;
import com.CounterX.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class KitchenServiceImpl implements KitchenService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public List<KitchenOrderDTO> getKitchenOrders() {

        List<KitchenOrderDTO> result = new ArrayList<>();

        // Only fetch today's orders that are relevant to the kitchen
        List<Order> todayOrders = orderRepository.findByOrderDate(LocalDate.now());

        for (Order order : todayOrders) {

            // Kitchen only cares about orders that have been paid and are active
            OrderStatus status = order.getOrderStatus();
            if (status == OrderStatus.PENDING_PAYMENT
                    || status == OrderStatus.CANCELLED) {
                continue;
            }

            // Fetch order items and map to KitchenItemDTO
            List<OrderItem> orderItems = orderItemRepository.findByOrderId(order.getOrderId());
            List<KitchenOrderDTO.KitchenItemDTO> items = orderItems.stream()
                    .map(i -> new KitchenOrderDTO.KitchenItemDTO(
                            i.getItemName(),
                            i.getQuantity()))
                    .collect(Collectors.toList());

            result.add(new KitchenOrderDTO(
                    order.getOrderId(),
                    order.getOrderType(),   //  order.getDailyOrderNumber(),
                    order.getOrderStatus(),
                    order.getTotalAmount(),
                    order.getOrderDateTime(),
                    items
            ));
        }

        return result;
    }

    @Override
    public Order updateKitchenStatus(
            Long orderId,
            String status) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Order Not Found"));

        order.setOrderStatus(mapFrontendStatus(status));

        return orderRepository.save(order);
    }

    /**
     * Maps whatever the frontend sends into the backend enum.
     * Both the exact backend names and the frontend aliases are handled.
     */
    private OrderStatus mapFrontendStatus(String raw) {
        if (raw == null) throw new IllegalArgumentException("Status must not be null");
        switch (raw.trim().toUpperCase()) {
            case "PENDING":
            case "PENDING_PAYMENT": return OrderStatus.PENDING_PAYMENT;
            case "CONFIRMED":
            case "PLACED":         return OrderStatus.PLACED;
            case "PREPARING":      return OrderStatus.PREPARING;
            case "READY":          return OrderStatus.READY;
            case "COMPLETED":
            case "SERVED":         return OrderStatus.SERVED;
            case "CANCELLED":      return OrderStatus.CANCELLED;
            default:
                throw new IllegalArgumentException(
                        "Unknown order status: " + raw +
                        ". Valid values: PENDING, CONFIRMED, PREPARING, READY, COMPLETED, CANCELLED");
        }
    }
}
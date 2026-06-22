package com.CounterX.repository;

import com.CounterX.entity.Order;
import com.CounterX.entity.OrderStatus;
import com.CounterX.entity.OrderType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // Get Orders by Date
    List<Order> findByOrderDate(LocalDate orderDate);

    // Get Today's Last Order
//    Order findTopByOrderDateOrderByDailyOrderNumberDesc(
//            LocalDate orderDate
//    );

    Order findTopByOrderDateOrderByOrderIdDesc(LocalDate orderDate);

    // Get Orders by Status
    List<Order> findByOrderStatus(OrderStatus orderStatus);

    // Get Orders by Type
    List<Order> findByOrderType(OrderType orderType);

    // Get Orders by Payment Status
    List<Order> findByPaymentStatus(
            com.CounterX.entity.PaymentStatus paymentStatus
    );

    long countByOrderStatus(OrderStatus status);

    @Query("""
SELECT COALESCE(SUM(o.totalAmount),0)
FROM Order o
WHERE o.orderDate = CURRENT_DATE
""")
    Double getTodayRevenue();

    @Query("""
SELECT COUNT(o)
FROM Order o
WHERE o.orderDate = CURRENT_DATE
""")
    Long getTodayOrderCount();
}
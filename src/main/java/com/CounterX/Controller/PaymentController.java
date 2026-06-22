package com.CounterX.Controller;

import com.CounterX.entity.Order;
import com.CounterX.entity.OrderStatus;
import com.CounterX.entity.PaymentStatus;
import com.CounterX.exception.ResourceNotFoundException;
import com.CounterX.repository.OrderRepository;
import com.CounterX.service.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin("*")
public class PaymentController {

    @Value("${razorpay.key.id}")
    private String razorpayKey;

    @Value("${razorpay.key.secret}")
    private String razorpaySecret;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentServiceImpl paymentService;



    @PostMapping("/create-order")
    public String createRazorpayOrder(@RequestBody Map<String, Object> data)
            throws RazorpayException {

        double amount =
                Double.parseDouble(data.get("amount").toString());

        RazorpayClient client =
                new RazorpayClient(razorpayKey, razorpaySecret);

        JSONObject options = new JSONObject();

        options.put("amount", (int) (amount * 100));
        options.put("currency", "INR");
        options.put("receipt",
                "receipt_" + System.currentTimeMillis());

        com.razorpay.Order order =
                client.orders.create(options);

        return order.toString();
    }


    /**
     * Marks the given order as paid and changes its status to PLACED.
     * The frontend should call this after a successful payment transaction.
     */
    @PutMapping("/{orderId}/complete/{paymentMethod}")
    public Order completePayment(@PathVariable Long orderId,@PathVariable String paymentMethod) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order Not Found"));
        order.setPaymentStatus(PaymentStatus.PAID);
        order.setOrderStatus(OrderStatus.PLACED);
        Order saved = orderRepository.save(order);

        paymentService.processPayment(orderId, paymentMethod);

        return saved;
    }
}

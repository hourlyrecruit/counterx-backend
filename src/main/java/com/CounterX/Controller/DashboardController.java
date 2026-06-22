package com.CounterX.Controller;

import com.CounterX.dto.AdminDashboardDTO;
import com.CounterX.dto.DashboardDTO;
import com.CounterX.entity.OrderStatus;
import com.CounterX.repository.OrderRepository;
import com.CounterX.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin("*")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @Autowired
    private OrderRepository orderRepository;


    @GetMapping("/today")
    public AdminDashboardDTO getTodayDashboard() {

        long totalOrders =
                orderRepository.getTodayOrderCount();

        double revenue =
                orderRepository.getTodayRevenue();

        long pending =
                orderRepository.countByOrderStatus(
                        OrderStatus.PLACED);

        long completed =
                orderRepository.countByOrderStatus(
                        OrderStatus.SERVED);

        long cancelled =
                orderRepository.countByOrderStatus(
                        OrderStatus.CANCELLED);

        return new AdminDashboardDTO(
                totalOrders,
                revenue,
                pending,
                completed,
                cancelled
        );
    }

    // Today's Dashboard
//    @GetMapping("/today")
//    public DashboardDTO getTodayDashboard() {
//
//        return dashboardService.getTodayDashboard();
//    }

    // Weekly Dashboard
    @GetMapping("/week")
    public List<DashboardDTO> getWeekDashboard() {

        return dashboardService.getWeekDashboard();
    }

    // Monthly Dashboard
    @GetMapping("/month")
    public List<DashboardDTO> getMonthDashboard() {

        return dashboardService.getMonthDashboard();
    }

}
package com.zentra.api.controller;

import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @GetMapping("/stats")
    public Map<String, Object> getDashboardStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalRevenue", 125000.0);
        stats.put("totalCustomers", 45);
        stats.put("totalProducts", 120);
        stats.put("totalOrders", 89);
        return stats;
    }
}
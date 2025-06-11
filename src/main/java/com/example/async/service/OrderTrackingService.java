package com.example.async.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OrderTrackingService {

    private final ConcurrentHashMap<String, String> orderStatusMap = new ConcurrentHashMap<>();
    private final AtomicInteger orderCounter = new AtomicInteger(0);

    public void updateOrderStatus(String orderId, String status) {
        orderStatusMap.put(orderId, status);
        System.out.println("Order " + orderId + " status updated to: " + status);
    }

    public String getOrderStatus(String orderId) {
        return orderStatusMap.getOrDefault(orderId, "Unknown");
    }

    public int getNextOrderId() {
        return orderCounter.incrementAndGet(); // Atomic increment
    }
}
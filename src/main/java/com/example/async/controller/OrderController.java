package com.example.async.controller;


import com.example.async.service.InventoryService;
import com.example.async.service.OrderTrackingService;
import com.example.async.service.OrderTrackingServiceTwo;
import com.example.async.service.OrderQueueService;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final InventoryService inventoryService;
    private final OrderTrackingService orderTrackingService;
    private final OrderQueueService orderQueueService;

    public OrderController(InventoryService inventoryService, OrderTrackingService orderTrackingService, OrderQueueService orderQueueService) {
        this.inventoryService = inventoryService;
        this.orderTrackingService = orderTrackingService;
        this.orderQueueService = orderQueueService;
    }

    @PostMapping("/place")
    public String placeOrder() {
        int orderId = orderTrackingService.getNextOrderId();
        orderTrackingService.updateOrderStatus(String.valueOf(orderId), "Pending");

        orderQueueService.placeOrder(String.valueOf(orderId));
        return "Order " + orderId + " placed successfully.";
    }

    @PostMapping("/bulk-update")
    public String bulkUpdateInventory(@RequestBody String[] orderIds) {
        inventoryService.bulkUpdateStock(Arrays.asList(orderIds));
        return "Bulk inventory update triggered.";
    }
}

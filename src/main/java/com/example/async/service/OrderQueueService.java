package com.example.async.service;

import org.springframework.stereotype.Service;
import java.util.concurrent.*;

@Service
public class OrderQueueService {

    private final BlockingQueue<String> orderQueue = new LinkedBlockingQueue<>();
    private final ExecutorService executorService = Executors.newFixedThreadPool(3);

    public void placeOrder(String orderId) {
        try {
            orderQueue.put(orderId);
            System.out.println("Order " + orderId + " added to the queue.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void startProcessingOrders() {
        for (int i = 0; i < 3; i++) {
            executorService.submit(() -> {
                while (true) {
                    try {
                        String orderId = orderQueue.take(); // Blocks if empty
                        System.out.println("Processing order " + orderId + " by " + Thread.currentThread().getName());
                        Thread.sleep(2000);
                        System.out.println("Order " + orderId + " processed.");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}


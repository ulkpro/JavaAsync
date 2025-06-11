package com.example.async.service;

import org.springframework.stereotype.Service;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.List;

@Service
public class InventoryService {

    private final ForkJoinPool forkJoinPool = new ForkJoinPool(4); // Pool with 4 parallel threads

    public void bulkUpdateStock(List<String> orderIds) {
        forkJoinPool.invoke(new InventoryUpdateTask(orderIds));
    }

    // RecursiveAction (No return value) for processing inventory in parallel
    private static class InventoryUpdateTask extends RecursiveAction {
        private final List<String> orderIds;
        private static final int THRESHOLD = 2;

        public InventoryUpdateTask(List<String> orderIds) {
            this.orderIds = orderIds;
        }

        @Override
        protected void compute() {
            if (orderIds.size() <= THRESHOLD) {
                orderIds.forEach(orderId -> {
                    System.out.println("Updating inventory for Order: " + orderId + " by " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000); // Simulate delay
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            } else {
                int mid = orderIds.size() / 2;
                InventoryUpdateTask task1 = new InventoryUpdateTask(orderIds.subList(0, mid));
                InventoryUpdateTask task2 = new InventoryUpdateTask(orderIds.subList(mid, orderIds.size()));

                invokeAll(task1, task2); // Process in parallel
            }
        }
    }
}

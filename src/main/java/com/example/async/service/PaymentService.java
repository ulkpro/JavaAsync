package com.example.async.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class PaymentService {
    private final ExecutorService executorService = Executors.newFixedThreadPool(3);

    public CompletableFuture<String> processPayment(String OrderID){
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Processing Payment for:" + OrderID);

            try{
                Thread.sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("Payment Processed");
            return "Payment Success";
        }, executorService);
    }
}

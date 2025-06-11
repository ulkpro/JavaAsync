package com.example.async.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service
public class EmailService {

    @Async
    public CompletableFuture<Void> sendOrderConfirmation(String emailAddress){
        return CompletableFuture.runAsync(() -> {
            System.out.println("Sending Email to:" + emailAddress);
            try{
                TimeUnit.SECONDS.sleep(2);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            System.out.println("Email Sent to:" + emailAddress);

            });
    }
}

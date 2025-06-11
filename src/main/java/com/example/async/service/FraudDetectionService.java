package com.example.async.service;

import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class FraudDetectionService {
    private final ReentrantLock lock = new ReentrantLock();

    public void detectFraud(){
        lock.lock();
        try{
            System.out.println("Detecting Fraud");
            Thread.sleep(2000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        lock.unlock();
    }
}

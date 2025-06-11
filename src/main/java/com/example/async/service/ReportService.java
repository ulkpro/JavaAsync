package com.example.async.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Service
public class ReportService {

    public void generateReports(){
        CountDownLatch latch = new CountDownLatch(3);

        new Thread(()->{
            try{
                System.out.println("Generating order report");
                Thread.sleep(2000);
                latch.countDown();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        });

        new Thread(()->{
            try{
                System.out.println("Generating report for payments");
                Thread.sleep(2000);
                latch.countDown();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        });

        try{
            latch.await();
            System.out.println("All reports generated");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

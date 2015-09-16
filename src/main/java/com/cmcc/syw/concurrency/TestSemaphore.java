package com.cmcc.syw.concurrency;

import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by sunyiwei on 2015/9/11.
 */
public class TestSemaphore {
    public static void main(String[] args) {
        final int count = Runtime.getRuntime().availableProcessors();
        ExecutorService service = Executors.newFixedThreadPool(count);
        final Semaphore semaphore = new Semaphore(count);


        int size = count * 2;
        for (int i = 0; i < size; i++) {
            service.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();

                        //sleep
                        Thread.currentThread().sleep(2000);
                        System.out.println(Thread.currentThread().getName() + ": Hi...");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        semaphore.release();
                    }
                }
            });
        }

        service.shutdown();
        try {
            while(!service.awaitTermination(10, TimeUnit.SECONDS)){
                    continue;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

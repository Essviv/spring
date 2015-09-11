package com.cmcc.syw.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 死锁的示例
 * Created by sunyiwei on 2015/9/11.
 */
public class DeadlockDemo {
    public static void main(String[] args) {
        final ExecutorService service = Executors.newSingleThreadExecutor();

        System.out.println("开始进入死锁...");
        service.submit(new Runnable() {
            @Override
            public void run() {
                service.submit(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(Thread.currentThread().getName() + ": 这句话永远也不会执行...");
                    }
                });

                System.out.println(Thread.currentThread().getName() + ": 这句话永远也不会执行...");
            }
        });

        service.shutdown();
        try {
            while(!service.awaitTermination(10, TimeUnit.SECONDS)){
                System.out.println("我在等...");
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}

package com.cmcc.syw.concurrency.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 测试synchronized关键字的重入性
 *
 * Created by sunyiwei on 2016/12/14.
 */
public class SynchronizedReentrantTester {
    public static void main(String[] args) throws InterruptedException {
        final SynchronizedReentrantTester srt = new SynchronizedReentrantTester();

        final int COUNT = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(COUNT);
        for (int i = 0; i < COUNT; i++) {
            executorService.submit(new Runnable() {
                final int SUB_COUNT = 10;

                @Override
                public void run() {
                    for (int j = 0; j < SUB_COUNT; j++) {
                        srt.methodA();
                    }
                }
            });
        }

        executorService.shutdown();
        if (!executorService.awaitTermination(100, TimeUnit.SECONDS)) {

        }
    }

    public synchronized void methodA() {
        System.out.println("MethodA: " + Thread.currentThread());

        //test reentrant
        methodB();
    }

    private synchronized void methodB() {
        System.out.println("MethodB: " + Thread.currentThread());
    }
}

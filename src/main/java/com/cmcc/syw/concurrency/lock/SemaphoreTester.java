package com.cmcc.syw.concurrency.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号量使用示例代码
 * <p>
 * Created by sunyiwei on 2016/12/14.
 */
public class SemaphoreTester {
    public static void main(String[] args) throws InterruptedException {
        final int COUNT = 10;
        final int THREAD_COUNT = 5 * COUNT;

        Semaphore semaphore = new Semaphore(COUNT);
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        for (int i = 0; i < THREAD_COUNT; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    final int SUB_COUNT = 10;

                    for (int j = 0; j < SUB_COUNT; j++) {
                        try {
                            semaphore.acquire();

                            try {
                                System.out.format("%s: 我拿到锁了！目前还有%d个许可.%n", Thread.currentThread(), semaphore.availablePermits());
                            } finally {
//                                semaphore.release();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }

        executorService.shutdown();
        if (!executorService.awaitTermination(100, TimeUnit.SECONDS)) {

        }
    }
}

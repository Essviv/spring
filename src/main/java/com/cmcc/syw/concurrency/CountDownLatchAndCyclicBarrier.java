package com.cmcc.syw.concurrency;

import java.util.concurrent.*;

/**
 * Created by sunyiwei on 2016/9/18.
 */
public class CountDownLatchAndCyclicBarrier {
    private static final int COUNT = 20;

    public static void main(String[] args) throws InterruptedException {
        //countDownLatch测试
        countDownLatch();

        //cyclicBarrier测试
        cyclicBarrier();
    }

    private static void countDownLatch() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(COUNT);
        ExecutorService executorService = Executors.newFixedThreadPool(COUNT);
        for (int i = 0; i < COUNT; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + ": 我来啦！");
                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await();
        executorService.shutdown();
        System.out.println("CountDownLatch跑完啦！");
    }

    private static void cyclicBarrier() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(COUNT, new Runnable() {
            @Override
            public void run() {
                System.out.println("所有线程都来了！BANG!");
            }
        });

        ExecutorService executorService = Executors.newFixedThreadPool(COUNT);
        for (int i = 0; i < COUNT; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        cyclicBarrier.await();

                        System.out.println(Thread.currentThread().getName() + ": 终于可以开始跑啦！");
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        executorService.shutdown();
        while (!executorService.isTerminated()) {
            try {
                executorService.awaitTermination(100, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("CyclicBarrier跑完啦！");
    }
}

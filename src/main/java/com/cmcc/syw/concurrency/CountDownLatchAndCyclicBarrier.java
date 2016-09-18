package com.cmcc.syw.concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by sunyiwei on 2016/9/18.
 */
public class CountDownLatchAndCyclicBarrier {
    private static final int COUNT = 16;

    public static void main(String[] args) throws InterruptedException {
        //countDownLatch测试
        countDownLatch();

        //cyclicBarrier测试
        cyclicBarrier();

        //semaphore测试
        semaphore();
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

    private static void semaphore() {
        final int WORKER_COUNT = COUNT * 2;  //工作线程数
        Semaphore semaphore = new Semaphore(COUNT / 2);  //资源数
        ExecutorService executorService = Executors.newFixedThreadPool(WORKER_COUNT);
        CountDownLatch countDownLatch = new CountDownLatch(WORKER_COUNT);

        for (int i = 0; i < WORKER_COUNT; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire(COUNT / 4); //获取资源

                        Thread.sleep(2000); //让我睡会儿

                        System.out.println(Thread.currentThread().getName() + ": 我获得了资源,开始工作啦!");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        System.out.println(Thread.currentThread().getName() + ": 我的事情做完了,把资源还给大家!");
                        semaphore.release(COUNT / 4);
                    }

                    countDownLatch.countDown();
                }
            });
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("你们大家做得都很好!我很满意!");
    }
}

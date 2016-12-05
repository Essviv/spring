package com.cmcc.syw.learn.cocurrent;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义线程池
 * <p>
 * Created by sunyiwei on 2016/12/3.
 */
public class CustomThreadFactory {
    public static void main(String[] args) throws InterruptedException {
//        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        ThreadFactory threadFactory = new ThreadFactory() {
            private final String prefix = "thread_";
            private final AtomicInteger atomicInteger = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, prefix + atomicInteger.getAndIncrement());
            }
        };

        List<Thread> threads = new LinkedList<>();

        final int COUNT = 100;
        CountDownLatch countDownLatch = new CountDownLatch(COUNT);

        for (int i = 0; i < COUNT; i++) {
            threads.add(threadFactory.newThread(() -> {
                System.out.println("CurrentThread: " + Thread.currentThread().getName());
                countDownLatch.countDown();
            }));
        }


        for (int i = 0; i < COUNT; i++) {
            threads.get(i).start();
        }

        countDownLatch.await();
    }
}

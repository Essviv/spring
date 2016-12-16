package com.cmcc.syw.concurrency.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * 测试synchronized关键字是否是公平的
 * <p>
 * 测试结果： 不是公平的，在线程释放monitor时，随机选取等待中的线程唤醒
 * <p>
 * Created by sunyiwei on 2016/12/15.
 */
public class SynchronizedFairnessTester {
    public static void main(String[] args) {
        final int COUNT = 5;

        SynchronizedFairnessTester sft = new SynchronizedFairnessTester();

        ExecutorService executorService = Executors.newFixedThreadPool(COUNT, new ThreadFactory() {
            private int index = 0;

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "Thread_" + index++);
            }
        });

        for (int i = 0; i < COUNT; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    sft.methodA();
                }
            });
        }
    }

    public synchronized void methodA() {
        System.out.println("Name: " + Thread.currentThread());
    }
}

package com.cmcc.syw.concurrency.waitandnofity;

import java.util.concurrent.CountDownLatch;

/**
 * 使用nofity和wait实现运动员跑步
 *
 * Created by sunyiwei on 2016/12/5.
 */
public class Athelete {
    public static void main(String[] args) throws InterruptedException {
        final Object lock = new Object();
        final int COUNT = 10;
        SharedValue sharedValue = new SharedValue();

        CountDownLatch cdl = new CountDownLatch(COUNT);
        for (int i = 0; i < COUNT; i++) {
            Thread t = new Runner(lock, sharedValue, cdl);
            t.start();
        }

        cdl.await();

        synchronized (lock) {
            lock.notifyAll();
        }
    }

    private static class Runner extends Thread {
        private final Object lock;
        private SharedValue sharedValue;
        private CountDownLatch countDownLatch;

        public Runner(Object lock, SharedValue sharedValue, CountDownLatch cdl) {
            this.lock = lock;
            this.countDownLatch = cdl;
            this.sharedValue = sharedValue;
        }

        @Override
        public void run() {
            synchronized (lock) {
                try {
                    System.out.println(Thread.currentThread().getName() + ": I'm all ready!");
                    countDownLatch.countDown();
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                sharedValue.setValue(sharedValue.getValue() + 1);
                System.out.println(Thread.currentThread().getName() + ": " + sharedValue.getValue());
            }
        }
    }

    private static class SharedValue {
        private int value;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}

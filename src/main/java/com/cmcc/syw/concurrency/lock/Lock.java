package com.cmcc.syw.concurrency.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by sunyiwei on 16/9/21.
 */
public class Lock {
    private static final ReentrantReadWriteLock LOCK = new ReentrantReadWriteLock();
    private static final int COUNT = 10;
    private static String message = "";

    public static void main(String[] args) throws InterruptedException {
        Thread readThread = new Thread(new ReadThread());
        Thread writeThreadA = new Thread(new WriteThread());
        Thread writeThreadB = new Thread(new WriteThread());

        readThread.start();
        writeThreadA.start();
        writeThreadB.start();

        readThread.join();
        writeThreadA.join();
        writeThreadB.join();
    }

    private static class ReadThread implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < COUNT; i++) {
                try {
                    LOCK.readLock().lock();
                    System.out.println(Thread.currentThread().getName() + ": " + "我拿到读锁了!");
                    System.out.println(Thread.currentThread().getName() + ":" + message);
                } finally {
                    LOCK.readLock().unlock();
                }
            }

        }
    }

    private static class WriteThread implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < COUNT; i++) {
                try {
                    LOCK.writeLock().lock();
                    System.out.println(Thread.currentThread().getName() + ": " + "我拿到写锁了!");
                    message += "a";
                } finally {
                    LOCK.writeLock().unlock();
                }
            }

        }
    }
}

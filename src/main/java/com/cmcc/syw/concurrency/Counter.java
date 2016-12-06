package com.cmcc.syw.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by sunyiwei on 2016/12/5.
 */
public class Counter {
    private int count = 0;
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        Counter counter = new Counter();

        final int COUNT = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(COUNT);
        for (int i = 0; i < COUNT; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    counter.inc();
                }
            });
        }

        stopExecutorService(executorService);
    }

    private static void stopExecutorService(ExecutorService es) {
        es.shutdown();

        try {
            while (!es.awaitTermination(100, TimeUnit.HOURS)) {

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int inc() {
        lock.writeLock().lock();

        try {
            return ++count;
        } finally {
            lock.writeLock().unlock();
        }
    }
}

package com.cmcc.syw.service.impl;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by sunyiwei on 2016/3/18.
 */
public class CustomReadWriteLock {
    private String fName;
    private String sName;
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public CustomReadWriteLock(String fName, String sName) {
        this.fName = fName;
        this.sName = sName;
    }

    public static void main(String[] args) {
        final CustomReadWriteLock crwl = new CustomReadWriteLock(rand(), rand());

        Runnable write = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    crwl.set(rand(), rand());
                }
            }
        };

        Runnable read = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    crwl.get();
                }
            }
        };

        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 8; i++) {
            service.submit(read);
        }

        for (int i = 0; i < 2; i++) {
            service.submit(write);
        }
    }

    private static String rand() {
        Random r = new Random();
        final int LENGTH = 10;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < LENGTH; i++) {
            sb.append((byte) ('a' + r.nextInt(26)));
        }

        return sb.toString();
    }

    public void set(String fName, String sName) {
        lock.writeLock().lock();

        try {
            this.fName = fName;
            this.sName = sName;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void get() {
        lock.readLock().lock();

        try {
            System.out.format("%s: FirstName = %s. %n", Thread.currentThread().getName(), fName);
            System.out.format("%s: SecondName = %s. %n", Thread.currentThread().getName(), sName);
        } finally {
            lock.readLock().unlock();
        }
    }
}

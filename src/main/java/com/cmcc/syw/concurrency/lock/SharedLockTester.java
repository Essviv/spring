package com.cmcc.syw.concurrency.lock;

import java.util.concurrent.Semaphore;

/**
 * 测试共享锁的实现机制
 *
 * Created by sunyiwei on 2016/12/14.
 */
public class SharedLockTester {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);

        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    semaphore.acquire();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread t1 = new Thread(r, "t1");
        Thread t2 = new Thread(r, "t2");
        Thread t3 = new Thread(r, "t3");
        Thread t4 = new Thread(r, "t4");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}

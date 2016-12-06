package com.cmcc.syw.concurrency.lock;

import java.util.concurrent.locks.*;

/**
 * 测试可重入锁是否为自旋锁
 *
 * 验证结果为: 不是
 *
 * Created by sunyiwei on 2016/12/6.
 */
public class ReentranLockTester {
    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();

        final int COUNT = 100;
        for (int i = 0; i < COUNT; i++) {
            Thread t1 = new CustomThread(reentrantLock);
            t1.start();
        }
    }

    private static class CustomThread extends  Thread{
        private final java.util.concurrent.locks.Lock lock;

        public CustomThread(java.util.concurrent.locks.Lock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            while(true){
                lock.lock();

                System.out.println(Thread.currentThread());

                lock.unlock();
            }
        }
    }
}


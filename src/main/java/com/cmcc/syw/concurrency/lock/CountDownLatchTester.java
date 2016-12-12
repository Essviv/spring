package com.cmcc.syw.concurrency.lock;

import java.util.concurrent.CountDownLatch;

/**
 * 使用countDownLatch测试共享锁的实现
 *
 * 对于cdl对象来讲,countDown操作是释放共享锁,而await操作是获取共享锁,共享锁只会在全部的共享锁被释放了之后才能获取到
 *
 * Created by sunyiwei on 2016/12/12.
 */
public class CountDownLatchTester {
    public static void main(String[] args) {
        CountDownLatch cdl = new CountDownLatch(1);

        //尝试释放锁的线程
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(15 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                cdl.countDown();
            }
        });

        t1.start();

        //尝试获取锁的线程
        Thread t2 = new Thread(new CustomRunnable(cdl));
        t2.start();

        //尝试获取锁的线程
        Thread t3 = new Thread(new CustomRunnable(cdl));
        t3.start();

        //尝试获取锁的线程
        Thread t4 = new Thread(new CustomRunnable(cdl));
        t4.start();
    }


    private static class CustomRunnable implements Runnable {
        private final CountDownLatch cdl;

        public CustomRunnable(CountDownLatch cdl) {
            this.cdl = cdl;
        }

        @Override
        public void run() {
            try {
                cdl.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "获取到了共享锁!");
        }
    }
}

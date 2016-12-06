package com.cmcc.syw.concurrency.waitandnofity;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by sunyiwei on 2016/12/6.
 */
public class OrderOutput2 {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition a = reentrantLock.newCondition();
        Condition b = reentrantLock.newCondition();
        Condition c = reentrantLock.newCondition();

        Thread ta = new CustomThread(reentrantLock, a, b, "a");
        Thread tb = new CustomThread(reentrantLock, b, c, "b");
        Thread tc = new CustomThread(reentrantLock, c, a, "c");

        ta.start();
        tb.start();
        tc.start();

        reentrantLock.lock();
        a.signal();
        reentrantLock.unlock();
    }

    private static class CustomThread extends Thread {
        private final Lock lock;
        private final Condition current;
        private final Condition next;
        private String value;

        public CustomThread(Lock lock, Condition current, Condition next, String value) {
            this.lock = lock;
            this.current = current;
            this.next = next;
            this.value = value;
        }

        @Override
        public void run() {
            final int COUNT = 10;
            lock.lock();

            for (int i = 0; i < COUNT; i++) {
                try {
                    current.await();

                    System.out.println(Thread.currentThread() + ": " + value);

                    next.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            lock.unlock();
        }

        public Condition getCurrent() {
            return current;
        }

        public Condition getNext() {
            return next;
        }

        public String getValue() {
            return value;
        }
    }
}



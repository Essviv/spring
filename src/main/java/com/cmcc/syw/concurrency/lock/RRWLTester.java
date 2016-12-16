package com.cmcc.syw.concurrency.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by sunyiwei on 2016/12/16.
 */
public class RRWLTester {
    public static void main(String[] args) {
        ReentrantReadWriteLock rrwl = new ReentrantReadWriteLock(false);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                rrwl.readLock().lock();  //让t1线程获取到读锁
                rrwl.readLock().unlock();
            }
        }, "t1");
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                rrwl.writeLock().lock();  //t2线程获取写锁，排队
                rrwl.writeLock().unlock();
            }
        }, "t2");
        t2.start();

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                rrwl.readLock().lock();  //t3线程获取读锁，排队
                rrwl.readLock().unlock();
            }
        }, "t3");
        t3.start();

        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                rrwl.readLock().lock();  //t3线程获取读锁，排队
                rrwl.readLock().unlock();
            }
        }, "t4");
        t4.start();
    }
}

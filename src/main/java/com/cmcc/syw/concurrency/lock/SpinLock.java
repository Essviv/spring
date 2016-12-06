package com.cmcc.syw.concurrency.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 实现自旋锁, 可以看到,这里的实现不是公平的,且不可重入, 且非常的占用CPU
 *
 * Created by sunyiwei on 2016/12/6.
 */
public class SpinLock implements Operator {
    private AtomicReference<Thread> current = new AtomicReference<>();

    public static void main(String[] args) {
        SpinLock spinLock = new SpinLock();

        final int COUNT = 100;
        for (int i = 0; i < COUNT; i++) {
            new CustomThread(spinLock).start();
        }
    }

    public void lock() {
        Thread currentThread = Thread.currentThread();
        while (!current.compareAndSet(null, currentThread)) { //获取锁失败,则开始轮询,直到成功
        }
    }

    public void unlock() {
        current.compareAndSet(Thread.currentThread(), null);
    }
}

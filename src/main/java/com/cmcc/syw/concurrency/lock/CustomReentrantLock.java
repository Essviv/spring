package com.cmcc.syw.concurrency.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;

/**
 * 基于AQS框架实现的可重入独占锁， 练习用, 不公平
 * <p>
 * Created by sunyiwei on 2016/12/14.
 */
public class CustomReentrantLock extends AbstractQueuedSynchronizer implements java.util.concurrent.locks.Lock {
    public static void main(String[] args) throws InterruptedException {
        final CustomReentrantLock crl = new CustomReentrantLock();
        final int COUNT = 10;
        int[] value = new int[1];

        ExecutorService executorService = Executors.newFixedThreadPool(COUNT);
        for (int i = 0; i < COUNT; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    final int TIMES = 10;
                    for (int i = 0; i < TIMES; i++) {
                        crl.lock();

                        try {
                            System.out.println(value[0]++);
                        } finally {
                            crl.unlock();
                        }
                    }
                }
            });
        }

        executorService.shutdown();
        if (!executorService.awaitTermination(100, TimeUnit.SECONDS)) {

        }
    }

    @Override
    protected boolean tryAcquire(int arg) {
        //0. 获取当前状态
        int state = getState();
        Thread currentThread = Thread.currentThread();

        if (state == 0) {
            if (compareAndSetState(0, arg)) {
                setExclusiveOwnerThread(currentThread);
                return true;
            }
        } else if (getExclusiveOwnerThread() == Thread.currentThread()) {
            int nextState = getState() + arg;
            setState(nextState);
            return true;
        }

        return false;
    }

    @Override
    protected boolean tryRelease(int arg) {
        if (getExclusiveOwnerThread() != Thread.currentThread()) {
            throw new IllegalMonitorStateException("Not current exclusive thread.");
        }

        int nextState = getState() - arg;
        boolean free = nextState == 0;

        if (free) {
            setExclusiveOwnerThread(null);
        }

        setState(nextState);
        return free;
    }

    @Override
    public void lock() {
        acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        release(1);
    }

    @Override
    public Condition newCondition() {
        return new ConditionObject();
    }
}

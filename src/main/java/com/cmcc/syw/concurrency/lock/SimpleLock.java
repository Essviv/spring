package com.cmcc.syw.concurrency.lock;

import java.util.Random;

/**
 * 自己实现的读写锁
 *
 * 1. 可重入: 读锁可重入,写锁可重入, 锁升级或者降级暂时不考虑
 *
 * 2. 写锁等待优先处理: 即当有线程在请求写锁时,读锁请求无法进来
 *
 * Created by sunyiwei on 2016/12/5.
 */
public class SimpleLock {
    private int writeRequest = 0;
    private int writer = 0;
    private int reader = 0;
    private Thread lockedBy = null;

    public static void main(String[] args) {
        SimpleLock simpleLock = new SimpleLock();
        Info info = new Info();

        CustomThread t1 = new CustomThread(info, simpleLock);
        CustomThread t2 = new CustomThread(info, simpleLock);
        CustomThread t3 = new CustomThread(info, simpleLock);

        t1.start();
        t2.start();
        t3.start();
    }

    public synchronized void readLock() {
        //allow to reentrant
        if (Thread.currentThread() == lockedBy) {
            reader++;
            System.out.println(this + ": Reentrant readLock.");

            return;
        }

        while (writer != 0 || writeRequest != 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        reader++;
        lockedBy = Thread.currentThread();
        System.out.println(this + ": ReadLock.");
    }

    public synchronized void unlockReadLock() {
        if (Thread.currentThread() == lockedBy) {
            reader--;

            System.out.println(this + ": UnlockReadLock.");

            if (reader == 0) {
                lockedBy = null;
                notifyAll();
            }
        }
    }

    public synchronized void writeLock() {
        //allow to reentrant
        if (Thread.currentThread() == lockedBy) {
            writer++;
            System.out.println(this + ": Reentrant writeLock.");
            return;
        }

        writeRequest++;
        while (writer != 0 || reader != 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        writeRequest--;
        lockedBy = Thread.currentThread();
        writer++;

        System.out.println(this + ": writeLock");
    }

    public synchronized void unlockWriteLock() {
        if (Thread.currentThread() == lockedBy) {
            writer--;
            System.out.println(this + ": UnlockWriteLock");

            if (writer == 0) {
                lockedBy = null;
                notifyAll();
            }
        }
    }

    @Override
    public String toString() {
        return Thread.currentThread().getName() + ": SimpleLock{" +
                "writeRequest=" + writeRequest +
                ", writer=" + writer +
                ", reader=" + reader +
                ", lockedBy=" + lockedBy.getName() +
                '}';
    }

    private static class CustomThread extends Thread {
        private final SimpleLock simpleLock;
        private Info info;

        public CustomThread(Info info, SimpleLock simpleLock) {
            this.info = info;
            this.simpleLock = simpleLock;
        }

        @Override
        public void run() {
            final int COUNT = 5;
            Random r = new Random();
            for (int i = 0; i < COUNT; i++) {
                if (r.nextBoolean()) {
                    simpleLock.readLock();
                    System.out.println(info.getName());
                    simpleLock.unlockReadLock();
                } else {
                    simpleLock.writeLock();
                    System.out.println(Thread.currentThread().getName() + " set new value: " + i);
                    info.setName(Thread.currentThread().getName() + "_" + i);
                    simpleLock.unlockWriteLock();
                }
            }
        }
    }

    private static class Info {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

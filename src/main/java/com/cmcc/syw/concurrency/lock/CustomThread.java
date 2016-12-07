package com.cmcc.syw.concurrency.lock;

/**
 * 测试各种锁的线程
 * <p>
 * Created by sunyiwei on 2016/12/6.
 */
public class CustomThread extends Thread {
    private static int value;
    private final Operator operator;

    public CustomThread(Operator operator) {
        this.operator = operator;
    }

    @Override
    public void run() {
        final int COUNT = 10;
        for (int i = 0; i < COUNT; i++) {
            operator.lock();

            System.out.println(Thread.currentThread() + ": " + String.valueOf(value++));

            operator.unlock();
        }
    }
}

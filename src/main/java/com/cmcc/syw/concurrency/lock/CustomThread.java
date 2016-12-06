package com.cmcc.syw.concurrency.lock;

/**
 * 测试各种锁的线程
 *
 * Created by sunyiwei on 2016/12/6.
 */
public class CustomThread extends Thread {
    private final Operator operator;

    public CustomThread(Operator operator) {
        this.operator = operator;
    }

    @Override
    public void run() {
        while (true) {
            operator.lock();

            System.out.println(Thread.currentThread());

            operator.unlock();
        }
    }
}

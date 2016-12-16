package com.cmcc.syw.concurrency.lock;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.locks.LockSupport;

/**
 * 使用LockSupport类
 * <p>
 * Created by sunyiwei on 2016/12/15.
 */
public class UseLockSupport {
    public static void main(String[] args) throws InterruptedException {
        final int COUNT = 100;

        Runnable r = new Runnable() {
            @Override
            public void run() {
                LockSupport.park();

                System.out.format("%s接收到了unpark指令，开始继续运行.%n", Thread.currentThread());
            }
        };

        ThreadFactory tf = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r);
            }
        };

        List<Thread> ts = new LinkedList<>();
        for (int i = 0; i < COUNT; i++) {
            Thread t = tf.newThread(r);
            ts.add(t);
            t.start();
        }

        Thread.sleep(1000);
        for (Thread t : ts) {
            LockSupport.unpark(t);
        }
    }
}

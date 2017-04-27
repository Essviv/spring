package com.cmcc.syw.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试ThreadLocal
 *
 * Created by sunyiwei on 2017/4/25.
 */
public class ThreadLocalTester {
    private static final AtomicInteger count = new AtomicInteger(0);

    private static ThreadLocal<Integer> localValue = new ThreadLocal<Integer>() {

    };

    public static void main(String[] args) {
        final int COUNT = 20;
        ExecutorService executorService = Executors.newFixedThreadPool(COUNT);
        ThreadLocalTester threadLocalTester = new ThreadLocalTester();

        for (int i = 0; i < COUNT; i++) {
            executorService.submit(new ThreadObj(threadLocalTester));
        }
    }

    public static Integer get() {
        return localValue.get();
    }

    private static class ThreadObj extends Thread {
        private final ThreadLocalTester threadLocalTester;

        /**
         * Allocates a new {@code Thread} object. This constructor has the same effect as
         * {@linkplain #Thread(ThreadGroup, Runnable, String) Thread} {@code (null, null, gname)},
         * where {@code gname} is a newly generated name. Automatically generated names are of the
         * form {@code "Thread-"+}<i>n</i>, where <i>n</i> is an integer.
         */
        public ThreadObj(ThreadLocalTester threadLocalTester) {
            this.threadLocalTester = threadLocalTester;
        }

        /**
         * If this thread was constructed using a separate <code>Runnable</code> run object, then
         * that <code>Runnable</code> object's <code>run</code> method is called; otherwise, this
         * method does nothing and returns. <p> Subclasses of <code>Thread</code> should override
         * this method.
         *
         * @see #start()
         * @see #stop()
         * @see #Thread(ThreadGroup, Runnable, String)
         */
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ": " + threadLocalTester.get());
        }
    }
}

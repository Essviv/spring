package com.cmcc.syw.concurrency;

import com.cmcc.syw.utils.Utils;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by sunyiwei on 2015/9/11.
 */
public class TestExecutorsHooks extends ThreadPoolExecutor {
    private ThreadLocal<Long> startTime = new ThreadLocal<Long>();
    private AtomicLong tasks = new AtomicLong();
    private AtomicLong totalTime = new AtomicLong();

    public TestExecutorsHooks(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        startTime.set(System.nanoTime());
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        try {
            long endTime = System.nanoTime();
            tasks.incrementAndGet();
            totalTime.addAndGet(endTime - startTime.get());
        }finally {
            super.afterExecute(r, t);
        }
    }

    @Override
    protected void terminated() {
        System.err.println("Total Time: " + TimeUnit.NANOSECONDS.toSeconds(totalTime.get()) + "S...");
        System.err.println("Total Task: " + tasks);

        super.terminated();
    }

    public static void main(String[] args) {
        ExecutorService service = new TestExecutorsHooks(10, 10, 0, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());

        final int count = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < count; i++) {
            final int index = i;
            service.submit(new Runnable() {
                @Override
                public void run() {
                    Utils.fibonaci(40 + index);
                }
            });
        }

        service.shutdown();
        try {
            while (!service.awaitTermination(10, TimeUnit.SECONDS)){
                continue;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

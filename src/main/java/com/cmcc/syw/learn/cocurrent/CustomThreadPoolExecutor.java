package com.cmcc.syw.learn.cocurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 扩展ThreadPoolExecutor，测试TPE的参数
 * <p>
 * 1. 重写了hook方法，在执行任务前后会输出日志
 * <p>
 * <p>
 * Created by patrick on 2017/1/4.
 */
public class CustomThreadPoolExecutor extends ThreadPoolExecutor {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = new CustomThreadPoolExecutor(100, 100, 0, TimeUnit.MICROSECONDS, new LinkedBlockingQueue<>());
        final int COUNT = 100;
        for (int i = 0; i < COUNT; i++) {
            executorService.submit(() -> System.out.println("ThreadName: " + Thread.currentThread()));
        }

        executorService.shutdown();
        while (!executorService.awaitTermination(1, TimeUnit.SECONDS)) {

        }
    }

    private static class DefaultThreadFactory implements ThreadFactory {
        private AtomicInteger count = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "Thread_" + count.getAndIncrement());
        }
    }

    public CustomThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, new DefaultThreadFactory());
    }

    public CustomThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public CustomThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, new DefaultThreadFactory(), handler);
    }

    public CustomThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        System.out.println("BeforeExecute: " + t.getName());
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        System.out.println("AfterExecute: " + r.toString());
    }
}

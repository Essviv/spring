package com.cmcc.syw.learn.cocurrent;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义线程池
 * <p>
 * Created by sunyiwei on 2016/12/3.
 */
public class CustomThreadFactory implements ThreadFactory {
    private final String prefix;
    private AtomicInteger atomicInteger = new AtomicInteger();

    public CustomThreadFactory(String prefix) {
        this.prefix = prefix;
    }

    /**
     * Constructs a new {@code Thread}.  Implementations may also initialize
     * priority, name, daemon status, {@code ThreadGroup}, etc.
     *
     * @param r a runnable to be executed by new thread instance
     * @return constructed thread, or {@code null} if the request to
     * create a thread is rejected
     */
    @Override
    public WorkerThread newThread(Runnable r) {
        String threadName = prefix + atomicInteger.getAndIncrement();
        System.out.println("Creating thread with name = " + threadName);

        return new WorkerThread(threadName);
    }
}

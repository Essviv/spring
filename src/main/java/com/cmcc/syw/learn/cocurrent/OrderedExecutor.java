package com.cmcc.syw.learn.cocurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 按顺序执行任务，且不同的任务可以指定在不同的线程中执行
 * <p>
 * Created by sunyiwei on 2016/12/3.
 */
public class OrderedExecutor implements Executor {
    private final Object lock = new Object();
    private final AtomicInteger atomInteger = new AtomicInteger();
    private BlockingQueue<TaskInfo> tasks = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        OrderedExecutor orderedExecutor = new OrderedExecutor();

        CustomThreadFactory customThreadFactory = new CustomThreadFactory("thread_");

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ": I'm running.");
            }
        };

        final int COUNT = 5;
        for (int i = 0; i < COUNT; i++) {
            WorkerThread workerThread = customThreadFactory.newThread(null);
            orderedExecutor.submit(workerThread, runnable);
        }

        for (int i = 0; i < COUNT; i++) {
            orderedExecutor.execute(runnable);
        }
    }

    /**
     * Executes the given command at some time in the future.  The command
     * may execute in a new thread, in a pooled thread, or in the calling
     * thread, at the discretion of the {@code Executor} implementation.
     *
     * @param command the runnable task
     * @throws RejectedExecutionException if this task cannot be
     *                                    accepted for execution
     * @throws NullPointerException       if command is null
     */
    @Override
    public void execute(Runnable command) {
        submit(new TaskInfo(new WorkerThread("defaultThread_" + atomInteger.getAndIncrement()), command));
    }

    public void submit(WorkerThread workerThread, Runnable runnable) {
        submit(new TaskInfo(workerThread, runnable));
    }

    public void submit(TaskInfo taskInfo) {
        try {
            synchronized (lock) {
                tasks.put(taskInfo);
            }

            executeInternal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void executeInternal() throws InterruptedException {
        synchronized (lock) {
            TaskInfo taskInfo = null;
            while ((taskInfo = tasks.poll()) != null) {
                WorkerThread workerThread = taskInfo.getWorkerThread();
                workerThread.put(taskInfo.getRunnable());
                workerThread.stopRunning();
                workerThread.join();
            }
        }
    }

    /**
     * 自定义线程池
     * <p>
     * Created by sunyiwei on 2016/12/3.
     */
    private static class CustomThreadFactory implements ThreadFactory {
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

    private class TaskInfo {
        private WorkerThread workerThread;
        private Runnable runnable;

        public TaskInfo(WorkerThread workerThread, Runnable runnable) {
            this.workerThread = workerThread;
            this.runnable = runnable;

            if (!this.workerThread.isAlive()) {
                this.workerThread.start();
            }
        }

        public WorkerThread getWorkerThread() {
            return workerThread;
        }

        public void setWorkerThread(WorkerThread workerThread) {
            this.workerThread = workerThread;
        }

        public Runnable getRunnable() {
            return runnable;
        }

        public void setRunnable(Runnable runnable) {
            this.runnable = runnable;
        }
    }
}

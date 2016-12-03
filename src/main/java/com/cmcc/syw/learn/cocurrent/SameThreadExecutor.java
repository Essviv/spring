package com.cmcc.syw.learn.cocurrent;

import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executor;

/**
 * 学习JAVA并发包
 * <p>
 * 实现executor接口，将所有的任务放在与调用者一样的线程中进行执行
 * <p>
 * Created by sunyiwei on 2016/12/3.
 */
public class SameThreadExecutor implements Executor {
    private final WorkerThread executingThread;

    public SameThreadExecutor(WorkerThread executingThread) {
        this.executingThread = executingThread;

        this.executingThread.start();
    }

    public static void main(String[] args) throws InterruptedException {
        SameThreadExecutor ste = new SameThreadExecutor(new WorkerThread("WorkingThread"));

        print("Start ", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));

        ste.execute(() -> {
            print("I'm running at ", null);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        print("End ", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
    }

    private static void print(String prefix, String suffix) {
        final String SEPERATOR = ": ";
        StringBuilder stringBuilder = new StringBuilder();

        if (StringUtils.isNotBlank(prefix)) {
            stringBuilder.append(prefix).append(SEPERATOR);
        }

        stringBuilder.append(Thread.currentThread().getName());

        if (StringUtils.isNotBlank(suffix)) {
            stringBuilder.append(SEPERATOR).append(suffix);
        }

        System.out.println(stringBuilder);
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
        try {
            executingThread.put(command);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}

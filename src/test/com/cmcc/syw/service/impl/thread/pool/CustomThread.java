package com.cmcc.syw.service.impl.thread.pool;

import java.util.concurrent.BlockingQueue;

/**
 * Created by sunyiwei on 2016/3/18.
 */
public class CustomThread extends Thread {
    private BlockingQueue<Runnable> queue;
    private boolean isRunning;

    public CustomThread(BlockingQueue<Runnable> queue, String name) {
        this.queue = queue;
        this.isRunning = true;
        setName(name);
    }

    public void doStop() {
        isRunning = false;
        this.interrupt();
    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                Runnable runnable = queue.take();
                runnable.run();

                synchronized (queue) {
                    queue.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

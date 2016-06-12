package com.cmcc.syw.service.impl.thread.pool;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by sunyiwei on 2016/3/18.
 */
public class CustomThreadPool {
    BlockingQueue<Runnable> queue;
    List<CustomThread> threads;
    private boolean isRunning;

    public CustomThreadPool(int nThreads, int nTasks) {
        queue = new LinkedBlockingDeque<Runnable>(nTasks);
        threads = new LinkedList<CustomThread>();
        isRunning = true;

        for (int i = 0; i < nThreads; i++) {
            threads.add(new CustomThread(queue, "Thread-" + i));
        }

        for (int i = 0; i < nThreads; i++) {
            threads.get(i).start();
        }
    }

    public void stop() {
        synchronized (queue) {
            isRunning = false;

            while(!queue.isEmpty()){
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for (int i = 0; i < threads.size(); i++) {
                threads.get(i).doStop();
            }
        }
    }

    public boolean execute(Runnable runnable) {
        if (!isRunning) {
            throw new IllegalArgumentException();
        }

        return queue.offer(runnable);
    }
}

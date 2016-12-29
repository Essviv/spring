package com.cmcc.syw.collections.queue.blocking;

import java.util.concurrent.BlockingQueue;

/**
 * 队列端点抽象
 * <p>
 * Created by patrick on 2016/12/29.
 */
public abstract class AbstractQueueEndPoint implements Runnable {
    protected volatile boolean isRunning;
    protected final BlockingQueue<String> queue;

    public AbstractQueueEndPoint(BlockingQueue<String> queue) {
        this.queue = queue;
        this.isRunning = true;
    }

    public final void stop() {
        isRunning = false;
    }
}

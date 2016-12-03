package com.cmcc.syw.learn.cocurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by sunyiwei on 2016/12/3.
 */
public class WorkerThread extends Thread {
    private BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();
    private volatile boolean isRunning = false;


    /**
     * Allocates a new {@code Thread} object. This constructor has the same
     * effect as {@linkplain #Thread(ThreadGroup, Runnable, String) Thread}
     * {@code (null, null, gname)}, where {@code gname} is a newly generated
     * name. Automatically generated names are of the form
     * {@code "Thread-"+}<i>n</i>, where <i>n</i> is an integer.
     */
    public WorkerThread(String name) {
        super(name);

        isRunning = true;
    }

    public void put(Runnable r) {
        try {
            queue.put(r);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stopRunning() {
        isRunning = false;
    }

    /**
     * If this thread was constructed using a separate
     * <code>Runnable</code> run object, then that
     * <code>Runnable</code> object's <code>run</code> method is called;
     * otherwise, this method does nothing and returns.
     * <p>
     * Subclasses of <code>Thread</code> should override this method.
     *
     * @see #start()
     * @see #stop()
     * @see #Thread(ThreadGroup, Runnable, String)
     */
    @Override
    public void run() {
        Runnable runnable = null;
        try {
            while (isRunning || !queue.isEmpty()) {
                if ((runnable = queue.poll(100, TimeUnit.MILLISECONDS)) != null) {
                    runnable.run();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
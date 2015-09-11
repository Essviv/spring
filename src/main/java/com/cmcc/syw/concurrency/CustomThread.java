package com.cmcc.syw.concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by sunyiwei on 2015/9/10.
 */
public class CustomThread extends Thread {
    private final int MAX_COUNT = 1000;
    private BlockingQueue<String> queue = new LinkedBlockingDeque<String>(MAX_COUNT);

    public static void main(String[] args) {
        CustomThread thread = new CustomThread();
        thread.start();

        try {
            Thread.sleep(2000);
            thread.cancel();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + ": " + "CustomThread has been interrupted...");
    }

    public void cancel() {
        interrupt();
    }

    @Override
    public void run() {
        int index = 0;
        try {
            while (!isInterrupted()) {
                queue.put("sunyiwei_" + index++);
                System.out.println("Remaining capability: " + queue.remainingCapacity());
            }
        } catch (InterruptedException e) {
            System.out.println("I'm interrupted...");
        }
    }
}



package com.cmcc.syw.service.impl;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by sunyiwei on 2016/3/16.
 */
public class Concurrency {
    public static void main(String[] args) {
        final List<Thread> threads = new LinkedList<Thread>();

        final int COUNT = 2;
        final int MAX = 1000;
        for (int i = 0; i < COUNT; i++) {
            Thread newThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    String threadName = Thread.currentThread().getName();

                    int index = 0;
                    while (index++ <= MAX) {
                        try {
                            System.out.println(threadName + " is still running, index = " + index);
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            System.out.println(threadName + " is interrupted.");
                            break;
                        }
                    }

                    System.out.println(threadName + " has completed.");
                }
            }, "Thread" + i);

            threads.add(newThread);
            newThread.start();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < COUNT; i++) {
                    try {
                        threads.get(i).join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}

package com.cmcc.syw.service.impl;

/**
 * Created by sunyiwei on 2016/3/16.
 */
public class Interference {
    private int counter = 0;

    public static void main(String[] args) {
        final Interference interference = new Interference();

        final int COUNT = 10000;

        Thread incrementThread =
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < COUNT; i++) {
                    interference.increment();
                }
            }
        });

        Thread decrementThread =
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < COUNT; i++) {
                    interference.decrement();
                }
            }
        });

        incrementThread.start();
        decrementThread.start();

        try {
            incrementThread.join();
            decrementThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(interference.getCounter());
    }

    public void increment() {
        counter++;
    }

    public void decrement() {
        counter--;
    }

    public int getCounter() {
        return counter;
    }
}

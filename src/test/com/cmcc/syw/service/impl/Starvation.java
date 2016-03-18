package com.cmcc.syw.service.impl;

import java.io.FileNotFoundException;

/**
 * Created by sunyiwei on 2016/3/17.
 */
public class Starvation {
    public static void main(String[] args) throws FileNotFoundException {
        final Starvation s1 = new Starvation();

        //greedy thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    s1.timeConsumingMethod();
                }
            }
        }, "greedy").start();

        //wont get lock easily
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    s1.saySth();
                }
            }
        }, "watcher").start();
    }

    public synchronized void timeConsumingMethod() {
        int COUNT = 1000000;

        while (COUNT-- > 10000) {
            if (calc(COUNT)) {
            }
        }
    }

    public synchronized void saySth() {
        System.out.println("Yes! Finally!");
    }

    private boolean calc(int n) {
        int sqrtValue = (int) Math.sqrt(n);
        for (int i = 2; i <= sqrtValue; i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }
}

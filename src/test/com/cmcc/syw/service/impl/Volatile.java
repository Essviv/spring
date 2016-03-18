package com.cmcc.syw.service.impl;

/**
 * Created by sunyiwei on 2016/3/16.
 */
public class Volatile implements Runnable {
    private volatile boolean stopFlag = false;

    public static void main(String[] args) {
        Volatile volatileObj = new Volatile();

        new Thread(volatileObj).start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        volatileObj.stop();
    }

    @Override
    public void run() {
        while (!stopFlag) {
            System.out.println("Go go go!");
        }

        System.out.println("Enough!");
    }

    public void stop() {
        stopFlag = true;
    }
}

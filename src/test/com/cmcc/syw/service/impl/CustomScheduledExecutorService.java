package com.cmcc.syw.service.impl;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by sunyiwei on 2016/3/18.
 */
public class CustomScheduledExecutorService {
    public static void main(String[] args) {
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(10);
        ses.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello world.");
            }
        }, 0, 1, TimeUnit.SECONDS);

        ses.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("WORLD HELL");
            }
        }, 0, 3, TimeUnit.SECONDS);
    }
}

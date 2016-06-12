package com.cmcc.syw.service.impl;

import java.util.concurrent.*;

/**
 * Created by sunyiwei on 2016/3/18.
 */
public class CustomRunnable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(100);

        Future<String> future = es.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Hello";
            }
        });

        Future future1 = es.submit(new Runnable() {
            @Override
            public void run() {
                return;
            }
        });

        es.shutdownNow();
        System.out.println(future.get());
    }
}

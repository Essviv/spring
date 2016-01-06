package com.cmcc.syw.concurrency;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by sunyiwei on 2015/9/11.
 */
public class ThreadPoolWithPoolResp {
    private final int count = Runtime.getRuntime().availableProcessors() + 1;
    private ExecutorService service = Executors.newFixedThreadPool(count);
    private Writer writer;

    public ThreadPoolWithPoolResp(){
        try {
            String filename = "C:\\Users\\Lenovo\\Desktop\\test.txt";
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void test(){
        Runnable longRunning = new Runnable() {
            @Override
            public void run() {
                long index = 0;
                while(index++ < 1000000L){
                    try {
                        writer.write(Thread.currentThread().getName() + ": " + index);
                        writer.write(System.getProperty("line.separator"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        for (int i = 0; i <count; i++) {
            service.submit(longRunning);
        }

        service.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Finally...");
            }
        });

        service.shutdown();
        try {
            while(!service.awaitTermination(20, TimeUnit.SECONDS)){

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        ThreadPoolWithPoolResp tpwp = new ThreadPoolWithPoolResp();
        tpwp.test();
    }
}

package com.cmcc.syw.service.impl.guard.block;

import java.util.Random;

/**
 * Created by sunyiwei on 2016/3/17.
 */
public class Producer implements Runnable {
    private Drop drop;

    public Producer(Drop drop) {
        this.drop = drop;
    }

    public static void main(String[] args) {
        Drop drop = new Drop();

        new Thread(new Consumer(drop), "consumer").start();
        new Thread(new Producer(drop), "producer").start();
    }

    @Override
    public void run() {
        String[] contents = {"Mares eat oats", "Does eat oats", "Little lambs eat ivy", "A kid will eat ivy too"};

        Random random = new Random();
        for (int i = 0; i < contents.length; i++) {
            drop.put(contents[i]);
            System.out.format("%s: %s. %n", Thread.currentThread().getName(), contents[i]);

            try {
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        drop.put("DONE");
    }
}

package com.cmcc.syw.learn.cocurrent;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by sunyiwei on 2016/12/5.
 */
public class ReadWriteLockPractise {
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private Info info = new Info();
    private int readLocks = 0;
    private int writeLocks = 0;

    public static void main(String[] args) {
        ReadWriteLockPractise rwlp = new ReadWriteLockPractise();

        final int COUNT = 5;
        ExecutorService executorService = Executors.newFixedThreadPool(COUNT);
        for (int i = 0; i < COUNT; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 3; j++) {
                        boolean isWrite = new Random().nextBoolean();
                        if (isWrite) {
                            rwlp.write();
                        } else {
                            rwlp.read();
                        }
                    }
                }
            });
        }

        stopExecutors(executorService);
    }

    private static void stopExecutors(ExecutorService executorService) {
        executorService.shutdown();

        try {
            if (!executorService.awaitTermination(1000, TimeUnit.SECONDS)) {

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void read() {
        readWriteLock.readLock().lock();

        try {
            readLocks++;

            print("gets the readLock. ReadLocks = " + readLocks);
            Thread.sleep(1000);
            print("Name = " + info.getName());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readLocks--;
            print("releases the readLock. ReadLocks = " + readLocks);
            readWriteLock.readLock().unlock();
        }
    }

    public void write() {
        readWriteLock.writeLock().lock();
        try {
            writeLocks++;
            print("gets the writeLock. WriteLocks = " + writeLocks);

            String newValue = randStr();

            Thread.sleep(1000);

            info.setName(newValue);

            print("新的值为" + newValue);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writeLocks--;
            print("releases the writeLock. WriteLocks = " + writeLocks);
            readWriteLock.writeLock().unlock();
        }
    }

    private void print(String message) {
        System.out.println(Thread.currentThread().getName() + ": " + message);
    }

    private String randStr() {
        StringBuilder sb = new StringBuilder();
        Random r = new Random();

        final int length = r.nextInt(30);
        for (int i = 0; i < length; i++) {
            sb.append((char) ('a' + r.nextInt(26)));
        }

        return sb.toString();
    }

    private static class Info {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

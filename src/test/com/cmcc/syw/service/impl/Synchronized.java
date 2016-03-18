package com.cmcc.syw.service.impl;

/**
 * Created by sunyiwei on 2016/3/16.
 */
public class Synchronized implements Runnable {
    final int COUNT = 100;
    private static byte[] lock1 = new byte[0];
    private static byte[] lock2 = new byte[0];

    public static void main(String[] args) {
        Synchronized s1 = new Synchronized();
        Synchronized s2 = new Synchronized();

        new Thread(s1).start();
    }

    @Override
    public void run() {
//        methodA();
//        methodB();
        methodA();
    }

    public synchronized void methodA() {
        int index = 0;
        while (index++ < COUNT) {
            System.out.println(Thread.currentThread().getName() + ": " + index);
        }
    }

    public synchronized void methodD() {
        int index = 0;
        while (index++ < COUNT) {
            System.out.println(Thread.currentThread().getName() + ": " + index);
        }
    }

    public static synchronized  void methodE(){
        int index = 0;
        while (index++ < 100) {
            System.out.println(Thread.currentThread().getName() + ": " + index);
        }
    }

    public void methodB(){
        synchronized (lock1){
            int index = 0;
            while (index++ < COUNT) {
                System.out.println(Thread.currentThread().getName() + ": " + index);
            }
        }
    }

    public synchronized static void methodC(){
        synchronized (lock2){
            int index = 0;
            while (index++ < 100) {
                System.out.println(Thread.currentThread().getName() + ": " + index);
            }
        }
    }
}

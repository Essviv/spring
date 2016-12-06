package com.cmcc.syw.concurrency.waitandnofity;

/**
 * 学习Java的等待通知机制
 *
 * Created by sunyiwei on 2016/12/5.
 */
public class WaitAndNotify {
    public static void main(String[] args) {
        final Object lock = new Object();
        Thread t1 = new PrintThread(1, lock);
        Thread t2 = new PrintThread(2, lock);

        t1.start();
        t2.start();

        System.out.println("Print Ctrl+C to stop.");
    }

    private static class PrintThread extends Thread {
        private final int num;
        private final Object obj;

        public PrintThread(int num, Object obj) {
            this.num = num;
            this.obj = obj;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (obj) {
                    try {
                        obj.notifyAll();
                        obj.wait();
                        System.out.println(num);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}

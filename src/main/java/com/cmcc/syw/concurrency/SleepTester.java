package com.cmcc.syw.concurrency;

/**
 * 测试线程的sleep方法
 *
 * 1. sleep()方法不需要在持有锁的情况下调用
 *
 * 2. 调用sleep()方法后,线程不会释放已经持有的锁
 *
 * Created by sunyiwei on 2017/4/27.
 */
public class SleepTester {
    public static void main(String[] args) {
        Object obj = new Object();
        new SleepyThread(obj).start();
        new SleepyThread(obj).start();
    }

    private static class SleepyThread extends  Thread{
        private final Object obj;

        public SleepyThread(Object obj) {
            this.obj = obj;
        }

        @Override
        public void run() {
            synchronized (obj){
                try {
                    Thread ct = Thread.currentThread();
                    sleep(1000);

                    System.out.println(ct.getName() + ": After sleep");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

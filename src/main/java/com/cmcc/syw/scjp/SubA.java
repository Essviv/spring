package com.cmcc.syw.scjp;

/**
 * Created by sunyiwei on 2016/10/25.
 */
public class SubA extends A {
    @Override
    public void test() {
    }

    @Override
    public void testB() {

    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Run");
                throw new RuntimeException("Problem inside another thread.");
            }
        }).start();

        System.out.println("End of method");
    }
}

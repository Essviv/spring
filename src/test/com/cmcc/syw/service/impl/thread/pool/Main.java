package com.cmcc.syw.service.impl.thread.pool;

/**
 * Created by sunyiwei on 2016/3/18.
 */
public class Main {
    public static void main(String[] args) {
        CustomThreadPool ctp = new CustomThreadPool(10, 100);

        for (int i = 0; i < 100; i++) {
            final int index = i;
            if (!ctp.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.format("Hello world from %s. %n", index);
                }
            })) {
                System.out.println("Submit failed.");
            }
        }

        ctp.stop();
    }
}

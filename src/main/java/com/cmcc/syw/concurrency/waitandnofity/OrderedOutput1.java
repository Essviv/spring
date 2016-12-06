package com.cmcc.syw.concurrency.waitandnofity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * 线程间顺序输出
 *
 * Created by sunyiwei on 2016/12/6.
 */
public class OrderedOutput1 {
    public static void main(String[] args) throws InterruptedException {
        final int COUNT = 3;
        List<Info> infos = new ArrayList<>();
        List<Semaphore> objs = new ArrayList<>();

        for (int i = 0; i < COUNT; i++) {
            Semaphore semaphore = new Semaphore(1);
            objs.add(semaphore);

            if (i != 0) {
                semaphore.acquire();
            }
        }

        for (int i = 0; i < COUNT; i++) {
            Info info = new Info(objs.get(i), objs.get((i + 1) % COUNT), String.valueOf(i));
            infos.add(info);
        }

        for (int i = 0; i < COUNT; i++) {
            final int INDEX = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Info info = infos.get(INDEX);
                    while (true) {
                        try {
                            info.current.acquire();

                            System.out.println(Thread.currentThread().getName() + ": " + info.getValue());

                            info.next.release();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }, String.valueOf(i)).start();
        }
    }

    private static class Info extends Thread {
        //当前的对象锁
        private Semaphore current;

        //下一个对象锁
        private Semaphore next;

        //要输出的值
        private String value;

        public Info(Semaphore current, Semaphore next, String value) {
            this.current = current;
            this.next = next;
            this.value = value;
        }

        public Object getCurrent() {
            return current;
        }

        public Object getNext() {
            return next;
        }

        public String getValue() {
            return value;
        }
    }
}

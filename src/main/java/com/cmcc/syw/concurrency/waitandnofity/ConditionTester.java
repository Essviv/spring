package com.cmcc.syw.concurrency.waitandnofity;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用condition接口实现生产者消费者
 * <p>
 * Created by sunyiwei on 2016/12/6.
 */
public class ConditionTester {
    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Queue<String> list = new LinkedBlockingQueue<>();

        Condition isEmpty = reentrantLock.newCondition();
        Condition isFull = reentrantLock.newCondition();

        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    reentrantLock.lock();

                    if (list.isEmpty()) {
                        try {
                            isEmpty.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    System.out.println(Thread.currentThread() + ": " + list.poll());
                    print(list);

                    isFull.signal();

                    reentrantLock.unlock();
                }
            }
        }, "Consumer");

        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                int index = 0;

                for (int i = 0; i < 10; i++) {
                    reentrantLock.lock();

                    if (list.size() == 1) {
                        try {
                            isFull.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    list.add("Msg_" + index++);
                    print(list);

                    isEmpty.signal();

                    reentrantLock.unlock();
                }
            }
        }, "Producer");


        consumer.start();
        producer.start();
    }

    private static void print(Queue<String> queue) {
        System.out.format("%s: Queue[Size = %d]%n", Thread.currentThread(), queue.size());
    }
}

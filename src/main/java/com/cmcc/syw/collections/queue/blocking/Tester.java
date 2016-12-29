package com.cmcc.syw.collections.queue.blocking;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 测试使用blockingQueue实现的生产者-消费者
 * <p>
 * Created by patrick on 2016/12/29.
 */
public class Tester {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue = new LinkedBlockingDeque<>();
        AbstractQueueEndPoint consumer = null;
        AbstractQueueEndPoint producer = null;

        Thread consumerThread = new Thread(consumer = new Consumer(queue), "Consumer");
        Thread producerThread = new Thread(producer = new Producer(queue), "Producer");

        consumerThread.start();
        producerThread.start();

        Thread.sleep(5000);
        consumer.stop();
        producer.stop();
    }
}

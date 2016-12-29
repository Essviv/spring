package com.cmcc.syw.collections.queue.blocking;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 使用BlockingQueue实现生产者
 * <p>
 * Created by patrick on 2016/12/29.
 */
public class Producer extends AbstractQueueEndPoint {

    public Producer(BlockingQueue<String> queue) {
        super(queue);
    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                String content = randStr();
                queue.offer(content, 1000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private String randStr() {
        Random r = new Random();
        final int length = r.nextInt(30);

        StringBuilder stringBuffer = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuffer.append((char) ('a' + r.nextInt(26)));
        }

        return stringBuffer.toString();
    }
}

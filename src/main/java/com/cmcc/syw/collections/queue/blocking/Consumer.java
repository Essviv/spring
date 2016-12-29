package com.cmcc.syw.collections.queue.blocking;

import org.apache.commons.lang.StringUtils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 使用blockingQueue实现消费者
 * <p>
 * Created by patrick on 2016/12/29.
 */
public class Consumer extends AbstractQueueEndPoint {
    public Consumer(BlockingQueue<String> queue) {
        super(queue);
    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                String content = queue.poll(1000, TimeUnit.MILLISECONDS);
                if (StringUtils.isNotBlank(content)) {
                    System.out.println(content);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

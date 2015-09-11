package com.cmcc.syw.concurrency;

import com.mysql.jdbc.TimeUtil;

import java.util.concurrent.*;

/**
 * Created by sunyiwei on 2015/9/11.
 */
public class TestRejectedHandler implements RejectedExecutionHandler {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service =
                new ThreadPoolExecutor(10, 10, 0,
                        TimeUnit.MILLISECONDS,
                        new LinkedBlockingDeque<Runnable>(10),
                        new TestRejectedHandler());

        for (int i = 0; i < 100; i++) {
            service.submit(new Runnable() {
                @Override
                public void run() {
//                    System.out.println(Thread.currentThread().getName() + ": Hello...");
                }
            });
        }

        service.shutdown();
        while (!service.awaitTermination(20, TimeUnit.SECONDS)){
            continue;
        }
    }

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("I'm rejected...T_T");
    }
}

package com.cmcc.syw.concurrency;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Future类的测试
 *
 * Created by sunyiwei on 2017/4/28.
 */
public class FutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final int COUNT = 3;
        ExecutorService executorService = Executors.newFixedThreadPool(COUNT);

        List<Future<String>> fs = new ArrayList<>(COUNT);
        for (int i = 0; i < COUNT; i++) {
            Future<String> f = executorService.submit((Callable<String>) () -> {
                Thread.sleep(rand());

                return Thread.currentThread().getName() + ": I'm sleepy.";
            });

            fs.add(f);
        }

        Iterator<Future<String>> it = fs.iterator();
        while(it.hasNext()){
            Future<String> f = it.next();
            System.out.println(f.get());
        }

        executorService.shutdownNow();
    }

    private static long rand() {
        return new Random().nextInt(5000);
    }
}

package com.cmcc.syw.concurrency;

import com.cmcc.syw.utils.Utils;

import java.util.concurrent.*;

/**
 * Created by sunyiwei on 2015/9/6.
 */
public class ExecutorTest {
    public static void main(String[] args) {
//        exeSeq();
//        exeCor();
//        exeWithBetterAlgorithm();
        try {
            final Long totalCount = 1000000000L;
            sum(totalCount);
            sumAsyn(totalCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void exeSeq() {
        System.out.println("Start seq: ");
        long startTime = System.currentTimeMillis();

        int start = 40;
        int end = 50;

        for (int i = start; i < end; i++) {
            System.out.println(i + ": " + Utils.fibonaci(i));
        }

        System.out.println("Execute sequencely: " + Double.valueOf(System.currentTimeMillis() - startTime) / 1000 + "s...");
    }

    private static void exeCor() {
        System.out.println("Start cor: ");
        long startTime = System.currentTimeMillis();

        final int threadSize = 10;

        final int start = 40;
        final int end = 50;

        final int subCount = (end - start) / threadSize;
        ExecutorService executorService = Executors.newFixedThreadPool(threadSize);
        for (int i = 0; i < threadSize; i++) {
            final int offset = start + i * subCount;
            Future<?> future = executorService.submit(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < subCount; j++) {
                        int current = offset + j;
                        System.out.println(current + ": " + Utils.fibonaci(current));
                    }
                }
            });
        }

        executorService.shutdown();
        try {
            while (!executorService.awaitTermination(3000, TimeUnit.MILLISECONDS)) {
                Thread.sleep(2000);
                System.out.println("Waiting...");
            }

            System.out.println("Execute concurrency: " + Double.valueOf(System.currentTimeMillis() - startTime) / 1000L + "s...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void exeWithBetterAlgorithm() {
        System.out.println("Start with better algorithm: ");
        long startTime = System.currentTimeMillis();

        int start = 40;
        int end = 50;

        long first = 0;
        long second = 0;
        long value = 0;
        for (int i = start; i < end; i++) {
            if (i == start) {
                first = Utils.fibonaci(i);
                value = first;
            } else if (i == start + 1) {
                second = Utils.fibonaci(i);
                value = second;
            } else {
                value = first + second;
                first = second;
                second = value;
            }

            System.out.println(i + ": " + value);
        }

        System.out.println("Execute sequencely: " + Double.valueOf(System.currentTimeMillis() - startTime) / 1000 + "s...");
    }

    public static void sum(Long totalCount) {
        long startTime = System.currentTimeMillis();

        Long sum = 0L;
        for (Long i = 0L; i < totalCount; i++) {
            sum += i;
        }

        System.out.println(sum);
        System.out.println("Sum sequencely: " + Double.valueOf(System.currentTimeMillis() - startTime) / 1000 + "s...");
    }

    public static void sumAsyn(Long totalCount) throws InterruptedException, ExecutionException {
        long startTime = System.currentTimeMillis();

        final int threadSize = 8;
        ExecutorService executorService = Executors.newFixedThreadPool(threadSize);
        CompletionService completionService = new ExecutorCompletionService(executorService);

        final Long subCount = totalCount / threadSize;

        //subSum
        for (int i = 0; i < threadSize; i++) {
            final Long offset = i * subCount;
            completionService.submit(new Callable<Long>() {
                @Override
                public Long call() throws Exception {
                    Long result = Long.valueOf(subCount) * Long.valueOf(offset);
                    for (Long j = 0L; j < subCount; j++) {
                        result += j;
                    }

                    return result;
                }
            });
        }

        Long sum = 0L;
        for (int i = 0; i < threadSize; i++) {
            Future<Long> future = completionService.take();
            sum += future.get();
        }

        System.out.println(sum);
        System.out.println("Sum concur: " + Double.valueOf(System.currentTimeMillis() - startTime) / 1000 + "s...");
        executorService.shutdown();
    }
}

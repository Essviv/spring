package com.cmcc.syw.redis;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 测试多线程使用单个jedis实例看不会看出现broken pipe的情况
 *
 * 测试结果: 出现了大量的broken pipe错误
 * 测试结论: jedis实例不是线程安全的(thread-safe)
 *
 * Created by sunyiwei on 16/5/26.
 */
public class MultiThread {
    public static void main(String[] args) {
        final String HOST = "localhost";
        final int PORT = 6379;
        final int COUNT = 100000;
        final int THREAD_COUNT = 100;

        JedisPool jedisPool = new JedisPool(HOST, PORT);
        final Jedis jedis = jedisPool.getResource();

        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        for (int i = 0; i < THREAD_COUNT; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < COUNT; j++) {
                        String key = randStr(10);
                        String value = randStr(10);

                        try {
                            jedis.set(key, value);
                        } catch (Exception e) {
                            System.out.format("Error: %s. %n", e.getMessage());
                        }
                    }
                }
            });
        }

        executorService.shutdown();
        while (!executorService.isTerminated()) {
            try {
                executorService.awaitTermination(1, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        jedis.close();
    }

    private static String randStr(int length) {
        StringBuilder sb = new StringBuilder();

        Random r = new Random();
        for (int i = 0; i < length; i++) {
            sb.append((char) ('a' + r.nextInt(26)));
        }

        return sb.toString();
    }
}

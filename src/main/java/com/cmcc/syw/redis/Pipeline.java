package com.cmcc.syw.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import java.util.Random;

/**
 * Created by sunyiwei on 16/4/10.
 */
public class Pipeline {
    public static void main(String[] args) {
        final String HOST = "localhost";
        final int PORT = 6379;
        final int COUNT = 100000;
        JedisPool jedisPool = new JedisPool(HOST, PORT);
        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();

            //no pipeline no transaction
            noPipelineNoTransaction(jedis, COUNT);

            //pipeline without trsanction
            pipelineNoTransaction(jedis, COUNT);

            //trsanction without pipeline
            transactionNoPipeline(jedis, COUNT);

            //pipeline with transaction
            pipelineTransaction(jedis, COUNT);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    private static void noPipelineNoTransaction(Jedis jedis, int count) {
        long current = System.currentTimeMillis();
        jedis.flushDB();

        for (int i = 0; i < count; i++) {
            String key = randStr(10);
            String value = randStr(10);
            jedis.set(key, value);
        }

        System.out.format("NoPipelineNoTransaction takes %#.2f seconds to set %d keys. %n",
                (double) (System.currentTimeMillis() - current) / 1000, count);
    }

    private static void pipelineNoTransaction(Jedis jedis, int count) {
        long current = System.currentTimeMillis();
        jedis.flushDB();

        redis.clients.jedis.Pipeline pipeline = jedis.pipelined();
        for (int i = 0; i < count; i++) {
            String key = randStr(10);
            String value = randStr(10);
            pipeline.set(key, value);
        }
        pipeline.sync();

        System.out.format("PipelineNoTransaction takes %#.2f seconds to set %d keys. %n",
                (double) (System.currentTimeMillis() - current) / 1000, count);
    }

    private static void pipelineTransaction(Jedis jedis, int count) {
        long current = System.currentTimeMillis();
        jedis.flushDB();

        redis.clients.jedis.Pipeline pipeline = jedis.pipelined();
        pipeline.multi();

        for (int i = 0; i < count; i++) {
            String key = randStr(10);
            String value = randStr(10);
            pipeline.set(key, value);
        }

        pipeline.exec();
        pipeline.sync();

        System.out.format("PipelineTransaction takes %#.2f seconds to set %d keys. %n",
                (double) (System.currentTimeMillis() - current) / 1000, count);
    }

    private static void transactionNoPipeline(Jedis jedis, int count) {
        long current = System.currentTimeMillis();
        jedis.flushDB();

        Transaction transaction = jedis.multi();
        for (int i = 0; i < count; i++) {
            String key = randStr(10);
            String value = randStr(10);
            transaction.set(key, value);
        }
        transaction.exec();

        System.out.format("TransactionNoPipeline takes %#.2f seconds to set %d keys. %n",
                (double) (System.currentTimeMillis() - current) / 1000, count);
    }

    private static String randStr(int length) {
        StringBuilder sb = new StringBuilder();

        Random r = new Random();
        for (int i = 0; i < length; i++) {
            sb.append((char)('a' + r.nextInt(26)));
        }

        return sb.toString();
    }
}

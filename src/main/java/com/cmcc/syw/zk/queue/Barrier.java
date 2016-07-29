package com.cmcc.syw.zk.queue;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by sunyiwei on 2016/7/29.
 */
public class Barrier extends SyncPrimitive {
    private String name;

    public Barrier(String path, String name, String address) {
        super(address, path);

        this.name = name;
    }

    public static void main(String[] args) {
        final int COUNT = 10;

        CountDownLatch enterCountDown = new CountDownLatch(COUNT);
        CountDownLatch leaveCountDown = new CountDownLatch(COUNT);

        ExecutorService executorService = Executors.newFixedThreadPool(COUNT);
        for (int i = 1; i <= COUNT; i++) {
            final int index = i;
            executorService.submit((Runnable) () -> {
                try {
                    Barrier barrier = new Barrier("/barrier", "/barrier/node" + index, "localhost:2181");
                    barrier.enter(enterCountDown);

                    print("开始等待所有人进入...");
                    enterCountDown.await();

                    print("所有人都来了, 开始干活！");
                    Random random = new Random();
                    int nSeconds = random.nextInt(10);
                    Thread.sleep(nSeconds * 1000);

                    print("开始等待所有人离开...");
                    barrier.leave(leaveCountDown);
                    leaveCountDown.await();

                    print("所有人都来了，撤！");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executorService.shutdown();
    }

    private static void print(String message) {
        System.out.format("%s: %s. %n", Thread.currentThread().getName(), message);
    }

    public boolean enter(CountDownLatch countDownLatch) {
        try {
            if (zooKeeper.exists(name, false) == null) {
                zooKeeper.create(name, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }

            List<String> list = zooKeeper.getChildren(root, true);
            countDownLatch.countDown();
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean leave(CountDownLatch countDownLatch) {
        try {
            zooKeeper.delete(name, 0);
            countDownLatch.countDown();
        } catch (InterruptedException | KeeperException e) {
            print("出错啦！" + e);
        }

        return false;
    }
}

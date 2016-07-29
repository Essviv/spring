package com.cmcc.syw.zk.queue;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;

import java.util.Random;

/**
 * Created by sunyiwei on 2016/7/28.
 */
public class Producer extends SyncPrimitive {
    public Producer(String address, String root) {
        super(address, root);
    }

    public static void main(String[] args) {
        String host = "localhost:2181";
        String root = "/producer";

        final int COUNT = 1;
        Producer producer = new Producer(host, root);
        for (int i = 0; i < COUNT; i++) {
            if (!producer.produce(randomStr(5))) {
                System.err.println("produce error!");
            }
        }
    }

    private static String randomStr(int length) {
        StringBuilder sb = new StringBuilder();
        Random r = new Random();

        for (int i = 0; i < length; i++) {
            sb.append((char) ('a' + r.nextInt(26)));
        }

        return sb.toString();
    }

    private boolean produce(String content) {
        if (zooKeeper != null) {
            try {
                zooKeeper.create(getEphemeralPath(root), content.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
                return true;
            } catch (KeeperException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        return false;
    }
}

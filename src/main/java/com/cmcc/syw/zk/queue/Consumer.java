package com.cmcc.syw.zk.queue;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.data.Stat;

import java.util.List;

/**
 * Created by sunyiwei on 2016/7/28.
 */
public class Consumer extends SyncPrimitive {
    public Consumer(String address, String path) {
        super(address, path);
    }

    public static void main(String[] args) {
        final String host = "localhost:2181";
        final String path = "/producer";

        Consumer consumer = new Consumer(host, path);
        consumer.consume();
    }

    public void consume() {
        while (true) {
            if (zooKeeper != null) {
                synchronized (mutex) {
                    try {
                        List<String> children = zooKeeper.getChildren(root, true);
                        if (children.size() == 0) {
                            mutex.wait();
                        } else {
                            Stat stat = new Stat();
                            String minElement = root + "/" + min(children);

                            String value = new String(zooKeeper.getData(minElement, false, stat));
                            System.out.println(value);

                            zooKeeper.delete(minElement, stat.getVersion());
                        }
                    } catch (KeeperException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private String min(List<String> children) {
        int min = Integer.MAX_VALUE;
        String minElement = null;

        for (String child : children) {
            int tmp = parse(child);
            if (tmp < min) {
                min = tmp;
                minElement = child;
            }
        }

        return minElement;
    }
}

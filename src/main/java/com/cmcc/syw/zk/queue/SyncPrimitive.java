package com.cmcc.syw.zk.queue;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by sunyiwei on 2016/7/28.
 */
public class SyncPrimitive implements Watcher {
    final protected Integer mutex = -1;
    protected ZooKeeper zooKeeper;

    protected String root;
    protected String address;
    private CountDownLatch countDownLatch = new CountDownLatch(1);

    public SyncPrimitive(String address, String root) {
        this.address = address;
        this.root = root;

        try {
            this.zooKeeper = new ZooKeeper(address, 3000, this);
            countDownLatch.await();

            Stat stat = zooKeeper.exists(root, false);
            if (stat == null) {
                zooKeeper.create(root, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (IOException | InterruptedException | KeeperException e) {
            e.printStackTrace();
            this.zooKeeper = null;
        }
    }

    @Override
    public void process(WatchedEvent event) {
        if (event.getType() == Event.EventType.None && event.getState() == Event.KeeperState.SyncConnected) {
            countDownLatch.countDown();
        } else {
            synchronized (mutex) {
                mutex.notifyAll();
            }
        }
    }

    protected String getEphemeralPath(String root) {
        return root + "/element";
    }

    protected int parse(String path) {
        return NumberUtils.toInt(path.substring(7));
    }
}

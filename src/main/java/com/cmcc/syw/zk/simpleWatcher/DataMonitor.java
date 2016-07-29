package com.cmcc.syw.zk.simpleWatcher;

import org.apache.commons.lang.StringUtils;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

/**
 * Created by sunyiwei on 2016/7/21.
 */
public class DataMonitor implements Watcher, AsyncCallback.StatCallback {
    private String path;
    private DataMonitorListener dataMonitorListener;
    private ZooKeeper zooKeeper;
    private volatile boolean dead;

    public DataMonitor(String path, DataMonitorListener dataMonitorListener, ZooKeeper zooKeeper) {
        this.path = path;
        this.dataMonitorListener = dataMonitorListener;
        this.zooKeeper = zooKeeper;

        zooKeeper.exists(path, true, this, null);
    }

    @Override
    public void process(WatchedEvent event) {
        String znode = event.getPath();

        if (event.getType() == Event.EventType.None) {
            switch (event.getState()) {
                case SyncConnected:
                    break;
                case Expired:
                    dead = true;
                    dataMonitorListener.closing();
                    break;
            }
        } else {
            if (StringUtils.isNotBlank(path) && path.equals(znode)) {
                zooKeeper.exists(path, true, this, null);
            }
        }
    }

    @Override
    public void processResult(int rc, String path, Object ctx, Stat stat) {
        boolean exist = false;
        switch (rc) {
            case KeeperException.Code.Ok:
                System.out.println("RcCode = OK");
                exist = true;
                break;
            case KeeperException.Code.NoNode:
                System.out.println("RcCode = NoNode");
                exist = false;
                break;
            case KeeperException.Code.NoAuth:
            case KeeperException.Code.SessionExpired:
                System.out.println("RcCode = NoAuth | SessionExpired");
                dead = true;
                dataMonitorListener.closing();
                return;
            default:
                System.out.println("RcCode = Default");
                zooKeeper.exists(path, true, this, null);
                break;
        }

        byte[] b = null;
        if (exist) {
            try {
                b = zooKeeper.getData(path, false, null);
            } catch (KeeperException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (b != null) {
            dataMonitorListener.exist(new String(b));
        }
    }

    public boolean isDead() {
        return dead;
    }
}

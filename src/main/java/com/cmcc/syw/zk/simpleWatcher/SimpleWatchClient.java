package com.cmcc.syw.zk.simpleWatcher;

import org.apache.commons.lang.StringUtils;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * ZK官方介绍JAVA API的例子
 * <p>
 * Created by sunyiwei on 2016/7/21.
 */
public class SimpleWatchClient implements Watcher, Runnable, DataMonitorListener {
    private DataMonitor dataMonitor;
    private String path;
    private ZooKeeper zooKeeper;
    private String host;
    private int port;

    public SimpleWatchClient(String path, String host, int port) {
        this.path = path;
        this.host = host;
        this.port = port;

        String connectString = host + ":" + port;
        try {
            this.zooKeeper = new ZooKeeper(connectString, 3000, this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.dataMonitor = new DataMonitor(path, this, zooKeeper);
    }

    public static void main(String[] args) {
        final String path = "/test";
        final String host = "localhost";
        final int port = 2181;

        SimpleWatchClient swc = new SimpleWatchClient(path, host, port);
        swc.run();
    }

    @Override
    public void run() {
        synchronized (this) {
            while (!dataMonitor.isDead()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void process(WatchedEvent event) {
        dataMonitor.process(event);
    }

    @Override
    public void exist(String data) {
        if (StringUtils.isNotBlank(data)) {
            System.out.println(data);
        }
    }

    @Override
    public void closing() {
        synchronized (this) {
            notifyAll();
        }
    }
}

package com.cmcc.syw.zk;

import com.google.gson.Gson;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * Created by sunyiwei on 2016/7/13.
 */
public class FirstZooKeeper {
    private static final Logger LOGGER = LoggerFactory.getLogger(FirstZooKeeper.class);

    private static Gson gson = new Gson();

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ZooKeeper zooKeeper = new ZooKeeper("localhost:2181", 3000, new CountDownWatcher(countDownLatch));

        //等待连接完成
        countDownLatch.await();

        //连接完成了，开始干活！
        String path = zooKeeper.create("/test", "hello_world".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
        LOGGER.info("{} has been created.", path);

        //显示点数据来看看
        String parent = "/";
        List<String> children = zooKeeper.getChildren(parent, true);

        for (String child : children) {
            Stat stat = new Stat();

            String subPath = parent + child;

            //校验一把
            Stat tmpStat = zooKeeper.exists(subPath, true);
            if (tmpStat == null) {
                continue;
            }

            String data = new String(zooKeeper.getData(subPath, true, stat));
            System.out.println(subPath + ":" + data);
            System.out.println(gson.toJson(stat));
        }

        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                CountDownLatch latch = new CountDownLatch(1);
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        th.start();
        th.join();
    }

    private static void testWatchEvent(ZooKeeper zooKeeper){
        testGetChildrenEvent(zooKeeper);

        testGetDataEvent(zooKeeper);

        testExistEvent(zooKeeper);
    }

    private static void testGetChildrenEvent(ZooKeeper zooKeeper) throws KeeperException, InterruptedException {
        String randomStr = randomStr(10);

        String path = zooKeeper.create(randomStr, randomStr(20).getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

    }

    private static void testExistEvent(ZooKeeper zooKeeper){

    }

    private static void testGetDataEvent(ZooKeeper zooKeeper){

    }

    private static String parseType(Watcher.Event.EventType eventType) {
        switch (eventType) {
            case None:
                return "没有";
            case NodeCreated:
                return "创建节点";
            case NodeDeleted:
                return "删除节点";
            case NodeDataChanged:
                return "节点数据变化";
            case NodeChildrenChanged:
                return "子节点变更";
            default:
                return "未知";
        }
    }

    private static String parseState(Watcher.Event.KeeperState state) {
        switch (state) {
            case Disconnected:
                return "失去连接";
            case SyncConnected:
                return "连接中";
            case AuthFailed:
                return "认证失败";
            case ConnectedReadOnly:
                return "只读连接";
            case SaslAuthenticated:
                return "SASL授权";
            case Expired:
                return "连接过期";
            default:
                return "未知状态";
        }
    }

    private static String randomStr(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("/");

        Random random = new Random();
        for (int i = 0; i < length; i++) {
            stringBuilder.append((char) (random.nextInt(26) + 'a'));
        }

        return stringBuilder.toString();
    }

    private static class CountDownWatcher implements Watcher {
        private CountDownLatch countDownLatch;

        public CountDownWatcher(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void process(WatchedEvent event) {
            if (event.getState() == Event.KeeperState.SyncConnected) {
                countDownLatch.countDown();
            }

            LOGGER.info("{}节点发生了{}事件.", event.getPath(), parseType(event.getType()));
        }
    }
}

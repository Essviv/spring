package com.cmcc.syw.concurrency.lock.spinlock;

import com.cmcc.syw.concurrency.lock.CustomThread;
import com.cmcc.syw.concurrency.lock.Operator;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * clh锁实践代码：当获取锁失败时，使用自旋方式等待
 * <p>
 * Created by sunyiwei on 2016/12/7.
 */
public class ClhSpinLock implements Operator {
    //前驱节点
    private ThreadLocal<Node> prev = new ThreadLocal<>();

    //当前节点
    private ThreadLocal<Node> current = new ThreadLocal<>();

    //末尾节点
    private AtomicReference<Node> tail = new AtomicReference<>();

    public static void main(String[] args) {
        ClhSpinLock clhLock = new ClhSpinLock();

        final int COUNT = 50;
        for (int i = 0; i < COUNT; i++) {
            new CustomThread(clhLock).start();
        }
    }

    public void lock() {
        //创建当前的节点
        Node currentNode = new Node();
        current.set(currentNode);

        //加入队列
        Node prevNode = tail.getAndSet(currentNode);
        prev.set(prevNode);

        //等待获取锁， 如果失败，则自旋等待
        while (prevNode != null && prevNode.isLocked()) {
        }

        //help GC
        prev.set(null);
    }

    public void unlock() {
        current.get().setLocked(false);
    }

    private class Node {
        //标识当前线程是否正在获取锁或已经获取到锁
        private volatile boolean isLocked;

        public Node() {
            this.isLocked = true;
        }

        public boolean isLocked() {
            return isLocked;
        }

        public void setLocked(boolean locked) {
            isLocked = locked;
        }
    }
}

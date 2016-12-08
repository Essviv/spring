package com.cmcc.syw.concurrency.lock.spinlock;

import com.cmcc.syw.concurrency.lock.CustomThread;
import com.cmcc.syw.concurrency.lock.Operator;
import com.mysql.jdbc.TimeUtil;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

/**
 * clh锁实践代码: 当获取锁失败时，使用阻塞方式
 * <p>
 * Created by sunyiwei on 2016/12/7.
 */
public class ClhBlockLock implements Operator {
    //前驱节点
    private ThreadLocal<Node> prev = new ThreadLocal<>();

    //当前节点
    private ThreadLocal<Node> current = new ThreadLocal<>();

    //末尾节点
    private AtomicReference<Node> tail = new AtomicReference<>();

    public static void main(String[] args) {
        ClhBlockLock clhLock = new ClhBlockLock();

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
        if (prevNode != null) {
            prevNode.setNext(currentNode);
        }

        //等待获取锁， 当获取失败时，阻塞等待
        while (prevNode != null && prevNode.isLocked()) {
            LockSupport.park(this);
        }

        //help GC
        prev.set(null);
    }

    public void unlock() {
        Node currentNode = current.get();
        currentNode.setLocked(false);

        Node nextNode = currentNode.getNext();
        if (nextNode != null) {
            LockSupport.unpark(nextNode.getThread());
        }
    }

    private class Node {
        private final Thread thread;
        //标识当前线程是否正在获取锁或已经获取到锁
        private volatile boolean isLocked;
        private Node next;

        public Node() {
            this.isLocked = true;
            this.next = null;
            this.thread = Thread.currentThread();
        }

        public boolean isLocked() {
            return isLocked;
        }

        public void setLocked(boolean locked) {
            isLocked = locked;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Thread getThread() {
            return thread;
        }
    }
}

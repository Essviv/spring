package com.cmcc.syw.concurrency.lock.spinlock;

import com.cmcc.syw.concurrency.lock.CustomThread;
import com.cmcc.syw.concurrency.lock.Operator;

import java.util.concurrent.atomic.AtomicReference;

/**
 * mcs锁实践代码
 * <p>
 * Created by sunyiwei on 2016/12/8.
 */
public class McsLock implements Operator {
    private ThreadLocal<Node> current = new ThreadLocal<>();
    private AtomicReference<Node> lock = new AtomicReference<>();

    public static void main(String[] args) {
        McsLock mcsLock = new McsLock();

        final int COUNT = 200;
        for (int i = 0; i < COUNT; i++) {
            new CustomThread(mcsLock).start();
        }
    }

    public void lock() {
        Node currentNode = new Node();
        current.set(currentNode);

        Node prevNode = lock.getAndSet(currentNode);
        if (prevNode != null) {
            prevNode.setNext(currentNode);
        }

        //在本地节点自旋，这是MCS锁与CLH锁本质的区别
        while (prevNode != null && currentNode.isLocked) {

        }
    }

    public void unlock() {
        Node currentNode = current.get();

        if (currentNode.getNext() == null) { //后面没有新的节点了, 当前节点有可能是队列的末尾
            if (lock.compareAndSet(currentNode, null)) { //设置成功了，说明当前节点真的是队列的末尾
                return;
            }

            while (currentNode.getNext() == null) { //设置失败了，说明在check-and-set的过程中，又有新的节点进来了，等待队列构建完成
                continue;
            }
        }

        currentNode.getNext().setLocked(false);
    }

    private class Node {
        private volatile boolean isLocked;
        private Node next;

        public Node() {
            this.isLocked = true;
            this.next = null;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public boolean isLocked() {
            return isLocked;
        }

        public void setLocked(boolean locked) {
            isLocked = locked;
        }
    }
}

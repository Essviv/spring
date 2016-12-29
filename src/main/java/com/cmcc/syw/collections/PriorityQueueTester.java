package com.cmcc.syw.collections;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 测试PriorityQueue的实现原理
 * <p>
 * Created by patrick on 2016/12/29.
 */
public class PriorityQueueTester {
    public static void main(String[] args) {
        Queue<Integer> queue = new PriorityQueue<>();
        final int COUNT = 10;
        for (int i = COUNT; i >= 0; i--) {
            queue.offer(i);
        }

        System.out.println(queue);
    }
}

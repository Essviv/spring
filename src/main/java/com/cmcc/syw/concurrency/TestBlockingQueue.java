package com.cmcc.syw.concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by sunyiwei on 2015/9/10.
 */
public class TestBlockingQueue {
    public static void main(String[] args) {
        final int MAX_COUNT = 1000;
        BlockingQueue<String> queue = new LinkedBlockingQueue<String>(MAX_COUNT);

        int index = 0;
        while(index++<MAX_COUNT){
            if(!queue.offer("sunyiwei_" + index)){
                System.out.println("Error!");
            }
        }

        System.out.println("Current index = " + index);
        System.out.println(queue.offer("Out of restriction..."));
    }
}

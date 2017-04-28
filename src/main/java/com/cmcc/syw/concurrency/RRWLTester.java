package com.cmcc.syw.concurrency;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by sunyiwei on 2017/4/28.
 */
public class RRWLTester {
    public static void main(String[] args) {
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        System.out.println(readWriteLock.getClass());
    }
}

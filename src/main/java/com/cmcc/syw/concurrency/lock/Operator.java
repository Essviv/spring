package com.cmcc.syw.concurrency.lock;

/**
 * LOCK操作接口
 *
 * Created by sunyiwei on 2016/12/6.
 */
public interface Operator {
    void lock();

    void unlock();
}

package com.cmcc.syw.zk;

/**
 * Created by sunyiwei on 2016/7/21.
 */
public interface DataMonitorListener {
    void exist(String data);

    void closing();
}

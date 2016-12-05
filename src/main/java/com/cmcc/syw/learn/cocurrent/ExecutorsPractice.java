package com.cmcc.syw.learn.cocurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * Created by sunyiwei on 2016/12/3.
 */
public class ExecutorsPractice {
    public static void main(String[] args) {
        ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        System.out.println(ses instanceof ScheduledThreadPoolExecutor);
    }
}

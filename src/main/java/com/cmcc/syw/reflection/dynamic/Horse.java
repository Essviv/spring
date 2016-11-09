package com.cmcc.syw.reflection.dynamic;

/**
 * 接口实现类
 * <p/>
 * Created by sunyiwei on 2016/11/9.
 */
public class Horse implements Animal {
    @Override
    public void run() {
        System.out.println("Horse is running.");
    }

    @Override
    public void yield() {
        System.out.println("Horse is yielding.");
    }
}

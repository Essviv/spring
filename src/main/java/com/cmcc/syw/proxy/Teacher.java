package com.cmcc.syw.proxy;

/**
 * 定义老师
 * <p/>
 * Created by sunyiwei on 2016/10/19.
 */
public class Teacher implements IPerson {
    @Override
    public void walk() {
        System.out.println("Teacher is walking...");
    }
}

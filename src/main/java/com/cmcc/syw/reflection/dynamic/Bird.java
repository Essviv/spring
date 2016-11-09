package com.cmcc.syw.reflection.dynamic;

/**
 * Created by sunyiwei on 2016/11/9.
 */
public class Bird implements Animal {
    @Override
    public void run() {
        System.out.println("Bird cannot run, but it can fly!");
    }

    @Override
    public void yield() {
        System.out.println("Bird is yielding!");
    }
}

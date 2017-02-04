package com.cmcc.syw.jvm.classloader.loader;

/**
 * Created by sunyiwei on 2017/1/17.
 */
public class Excel implements Assembly {
    @Override
    public void start() {
        System.out.println("Print class " + Excel.class.getSimpleName());
    }
}

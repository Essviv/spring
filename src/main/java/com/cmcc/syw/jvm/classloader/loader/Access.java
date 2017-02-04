package com.cmcc.syw.jvm.classloader.loader;

/**
 * Created by sunyiwei on 2017/1/17.
 */
public class Access implements Assembly {
    @Override
    public void start() {
        System.out.println("Print class " + Access.class.getSimpleName());
    }
}

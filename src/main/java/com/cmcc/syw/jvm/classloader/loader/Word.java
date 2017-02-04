package com.cmcc.syw.jvm.classloader.loader;

/**
 * Created by sunyiwei on 2017/1/17.
 */
public class Word implements Assembly{
    static{
        System.out.println(Word.class.getSimpleName() + ": static block initialization.");
    }

    @Override
    public void start() {
        System.out.println("Print Class " + Word.class.getSimpleName());
    }
}

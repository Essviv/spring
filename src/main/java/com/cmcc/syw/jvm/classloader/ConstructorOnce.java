package com.cmcc.syw.jvm.classloader;

/**
 * 只能被调用一次构造函数的类
 *
 * Created by sunyiwei on 2016/11/13.
 */
public class ConstructorOnce {
    private static boolean once = false;

    public ConstructorOnce() {
        if (!once) {
            once = true;
        } else {
            throw new RuntimeException("Cannot construct more than once.");
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        SimpleClassLoader scl1 = new SimpleClassLoader("/Users/sunyiwei/Desktop/");
        Class clazz1 = scl1.loadClass("com.cmcc.syw.jvm.classloader.ConstructorOnce");
        Object obj1 = clazz1.newInstance();


        SimpleClassLoader scl2 = new SimpleClassLoader("/Users/sunyiwei/Desktop/");
        Class clazz2 = scl1.loadClass("com.cmcc.syw.jvm.classloader.ConstructorOnce");
        Object obj2 = clazz2.newInstance();
    }
}

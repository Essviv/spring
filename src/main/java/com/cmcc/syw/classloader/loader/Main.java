package com.cmcc.syw.classloader.loader;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by sunyiwei on 2017/1/17.
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, MalformedURLException {
        String className = args[0];

        ClassLoader classLoader = new URLClassLoader(new URL[]{new URL("file:/Users/sunyiwei/Desktop/")});
        Class clazz = classLoader.loadClass(className);
        Assembly assembly = (Assembly) clazz.newInstance();
        assembly.start();

        ClassLoader classLoader1 = new URLClassLoader(new URL[]{new URL("file:/Users/sunyiwei/Desktop/")});
        Class clazz1 = classLoader1.loadClass(className);
        Assembly assembly1 = (Assembly) clazz1.newInstance();
        assembly1.start();

        System.out.println(clazz.getClassLoader());
        System.out.println(assembly.getClass().getClassLoader());

        System.out.println(clazz1.getClassLoader());
        System.out.println(assembly1.getClass().getClassLoader());
    }
}

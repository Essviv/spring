package com.cmcc.syw.classloader;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * classLoader的简单实现
 *
 * Created by sunyiwei on 2016/11/13.
 */
public class SimpleClassLoader extends ClassLoader {
    //搜索类名的目录
    private String searchDir;

    public SimpleClassLoader(ClassLoader parent, String searchDir) {
        super(parent);
        this.searchDir = searchDir;
    }

    public SimpleClassLoader(String searchDir) {
        this.searchDir = searchDir;
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String searchDir = "/Users/sunyiwei/Desktop";
        String className = "com.cmcc.syw.model.Person";

        SimpleClassLoader simpleClassLoader = new SimpleClassLoader(searchDir);
        Class clazz = simpleClassLoader.loadClass(className);
        if (clazz != null) {
            System.out.println("DefiningClassLoader: " + clazz.getClassLoader());

            Object obj = clazz.newInstance();
            System.out.println(obj.toString());
        }
    }

//    @Override
//    public Class<?> loadClass(String name) throws ClassNotFoundException {
//        return findClass(name);
//    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String classFileName = buildPath(name);
        System.out.println("ClassFileName: " + classFileName);

        try {
            byte[] bytes = FileUtils.readFileToByteArray(new File(classFileName));
            return defineClass(name, bytes, 0, bytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException(e.getMessage());
        }
    }

    private String buildPath(String name) {
        return searchDir + (searchDir.endsWith("/") ? "" : "/") + name.replace(".", "/") + ".class";
    }
}

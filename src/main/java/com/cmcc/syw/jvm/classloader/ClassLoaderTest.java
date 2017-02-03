package com.cmcc.syw.jvm.classloader;

import com.cmcc.syw.reflection.BeanToMapper;

import java.io.IOException;
import java.io.InputStream;

/**
 * 了解classloader的双亲委派模型与加载过程
 *
 * Created by sunyiwei on 2016/11/13.
 */
public class ClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        clTest();
    }

    private static void clTest() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader cl = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                String filename = name.substring(name.lastIndexOf(".") + 1) + ".class";

                InputStream is = getClass().getResourceAsStream(filename);
                if (is == null) {
                    return super.loadClass(name);
                }

                try {
                    byte[] b = new byte[is.available()];
                    is.read(b);

                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }
        };

        Object obj = cl.loadClass("com.cmcc.syw.jvm.classloader.ClassLoaderTest").newInstance();
        System.out.println(obj.getClass());
        System.out.println(obj instanceof com.cmcc.syw.jvm.classloader.ClassLoaderTest);
    }

    private static void arrayTest() throws ClassNotFoundException {
        Class clazz = BeanToMapper.class.getClassLoader().loadClass("com.cmcc.syw.reflection.ReflectionClassInfo");
        System.out.println(clazz.getSimpleName());

        Integer[] ints = new Integer[5];
        ClassLoader cl = ints.getClass().getClassLoader();

        Integer intElem = new Integer(5);
        ClassLoader icl = intElem.getClass().getClassLoader();

        int[] intPrim = new int[5];
        ClassLoader ipcl = intPrim.getClass().getClassLoader();

        System.out.println("CL:" + cl);
        System.out.println("ICL: " + icl);
        System.out.println("IPCL: " + ipcl);

    }
}

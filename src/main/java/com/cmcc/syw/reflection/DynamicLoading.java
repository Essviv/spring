package com.cmcc.syw.reflection;


import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 测试动态加载机制
 *
 * Created by sunyiwei on 2016/11/8.
 */
public class DynamicLoading {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        String className = "com.cmcc.syw.reflection.DynamicLoading$Info";
        Class clazz = Class.forName(className);

        System.out.println(className);

//        cons(clazz);
        cons2(clazz);

        System.out.println("Correct");
    }

    private static Object cons(Class clazz) throws IllegalAccessException, InstantiationException {
        if (clazz.isArray()) {
            return Array.newInstance(clazz.getComponentType(), 5);
        } else {
            return clazz.newInstance();
        }
    }

    private static Object cons2(Class clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if (clazz.isArray()) {
            return Array.newInstance(clazz.getComponentType(), 5);
        } else {
            Constructor constructor = clazz.getConstructor(DynamicLoading.class, String.class, int.class);
            return constructor.newInstance(new DynamicLoading(), "hell", 27);
        }
    }

    private class Info {
        private String name;
        private int age;

        public Info(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}

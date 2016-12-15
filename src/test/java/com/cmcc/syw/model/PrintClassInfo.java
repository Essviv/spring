package com.cmcc.syw.model;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 测试JUC中AQS框架中可以被重写的方法
 * <p>
 * Created by sunyiwei on 2016/12/8.
 */
public class PrintClassInfo {
    public static void main(String[] args) {
        Class clazz = AbstractQueuedSynchronizer.class;
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (!isPrivateOrFinal(method)) {
                System.out.println(method.getName());
            }
        }
    }

    private static boolean isPrivateOrFinal(Method method) {
        int modifiers = method.getModifiers();

        if (Modifier.isFinal(modifiers)) {
            return true;
        } else if (Modifier.isPrivate(modifiers)) {
            return true;
        } else {
            return false;
        }
    }
}

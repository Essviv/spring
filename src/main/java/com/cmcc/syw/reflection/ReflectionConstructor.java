package com.cmcc.syw.reflection;

import com.google.gson.Gson;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static com.cmcc.syw.reflection.ReflectionUtils.parseExecutable;

/**
 * 通过反射获取构造信息
 *
 * Created by sunyiwei on 2016/10/30.
 */
public class ReflectionConstructor {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<ReflectionClass> clazz = ReflectionClass.class;
        Constructor[] cstcs = clazz.getDeclaredConstructors();
        for (Constructor cstc : cstcs) {
            System.out.println(parseExecutable(cstc));
            System.out.println(parseTypeInfo(cstc.getParameterTypes()));
        }

        ReflectionClass instance = clazz.getConstructor(String.class, int.class).newInstance("Hello", 5);
        System.out.println(new Gson().toJson(instance));

        Constructor cstc0 = cstcs[0];
        System.out.println(cstc0.getDeclaringClass().getName());
    }

    private static String parseTypeInfo(Class[] clazzInfos) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Class clazzInfo : clazzInfos) {
            stringBuilder.append(clazzInfo.getName()).append(" ");
        }

        return stringBuilder.toString();
    }
}

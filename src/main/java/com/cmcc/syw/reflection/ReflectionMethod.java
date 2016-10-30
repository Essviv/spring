package com.cmcc.syw.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static com.cmcc.syw.reflection.ReflectionUtils.parseExecutable;

/**
 * 反射方法的相关信息
 *
 * Created by sunyiwei on 2016/10/30.
 */
public class ReflectionMethod {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Class clazz = ReflectionClass.class;
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(parseExecutable(method));

            if (method.getParameterCount() == 0) {
                System.out.format("Invoke result of %s is %s.%n", method.getName(), invoke(clazz, method));
            }

            System.out.println("++++++++++++++");
        }
    }

    private static Object invoke(Class<ReflectionClass> clazz, Method method) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<ReflectionClass> constructor = clazz.getConstructor(String.class, int.class);
        ReflectionClass reflectionClass = constructor.newInstance("hello", 5);

        method.setAccessible(true);
        return method.invoke(reflectionClass);
    }

}

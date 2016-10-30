package com.cmcc.syw.reflection;

import com.cmcc.syw.model.User;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 打印指定类的所有类名
 *
 * Created by sunyiwei on 2016/10/30.
 */
public class PrintClassMethods {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        User user = new User();
        Method[] methods = User.class.getMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }

        System.out.println("++++++++++++++");

        methods = User.class.getDeclaredMethods();
        for (Method method : methods) {
            String methodName = method.getName();

            System.out.println(methodName);

            if (method.getParameterCount() == 0) {
                method.setAccessible(true);
                method.invoke(user);
            }
        }

        System.out.println("++++++++++++++");
        Field[] fields = User.class.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }
    }
}

package com.cmcc.syw.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 使用java原生的方式实现动态代理
 * <p/>
 * Created by sunyiwei on 2016/10/19.
 */
public class DynamicTeacherProxyJavaNative {
    public static void main(String[] args) {
        IPerson iPerson = (IPerson) Proxy.newProxyInstance(Teacher.class.getClassLoader(), new Class[]{IPerson.class}, new MyInvocationHandler(new Teacher()));
        System.out.println(Proxy.isProxyClass(iPerson.getClass()));

        iPerson.walk();
    }

    private static class MyInvocationHandler implements InvocationHandler {
        private Teacher teacher;

        public MyInvocationHandler(Teacher teacher) {
            this.teacher = teacher;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            System.out.println("I'm a dynamic teacher proxy.");

            Object obj = method.invoke(teacher, args);

            System.out.println("Teacher proxy completes walk.");

            return obj;
        }
    }
}

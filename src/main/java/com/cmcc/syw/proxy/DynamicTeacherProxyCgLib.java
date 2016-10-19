package com.cmcc.syw.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 使用cglib的方式实现动态代理
 * <p/>
 * Created by sunyiwei on 2016/10/19.
 */
public class DynamicTeacherProxyCgLib {
    public static void main(String[] args) {
        Teacher teacher = new Teacher();
        IPerson iPerson = (IPerson) createProxy(Teacher.class, teacher);

        iPerson.walk();
    }

    private static Object createProxy(Class clz, Object object) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clz);
        
//        enhancer.setCallbackFilter(new CallbackFilter() {
//            @Override
//            public int accept(Method method) {
//                return 0;
//            }
//        });

        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println("I'm a dynamic cglib proxy instance.");

                Object returnObj = proxy.invoke(object, args);

                System.out.println("Teacher stops walk.");

                return returnObj;
            }
        });

        return enhancer.create();
    }
}

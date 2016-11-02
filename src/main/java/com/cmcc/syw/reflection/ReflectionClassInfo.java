package com.cmcc.syw.reflection;

import org.apache.commons.lang.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static com.cmcc.syw.reflection.ReflectionUtils.parseConstruct;
import static com.cmcc.syw.reflection.ReflectionUtils.parseExecutable;
import static com.cmcc.syw.reflection.ReflectionUtils.parseModifiers;
import static com.cmcc.syw.reflection.ReflectionUtils.parseProp;

/**
 * 通过反射获取类信息 <p/> Created by sunyiwei on 2016/10/30.
 */
public class ReflectionClassInfo {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        Class<ReflectionClass> clazz = ReflectionClass.class;

        //isInstance
        System.out.println(clazz.isInstance(Object.class));
        System.out.println(clazz.isInstance(new ReflectionClass("", 3)));

        //打印属性信息
        printFields(clazz, new ReflectionClass("Hello_world", 27));

        //包信息
        Package packageInfo = clazz.getPackage();
        System.out.println(packageInfo);

        //注解信息
        Annotation[] annotations = clazz.getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(parseAnnotation(annotation));
        }

        //修饰符信息
        int modifiers = clazz.getModifiers();

        //超类信息
        Class superClazz = clazz.getSuperclass();

        //接口信息
        Class[] interfaces = clazz.getInterfaces();
        System.out.println(concat(modifiers, clazz.getSimpleName(), superClazz, interfaces));

        //构造函数
        Constructor[] cstcs = clazz.getDeclaredConstructors();
        for (Constructor cstc : cstcs) {
            if (cstc.getParameterCount() == 0) {
                cstc.setAccessible(true);
                ReflectionClass reflectionClass = (ReflectionClass) cstc.newInstance();
                System.out.println(reflectionClass);
            }

            System.out.println(parseConstruct(cstc));
        }

        //方法信息
        for (Method method : clazz.getDeclaredMethods()) {
            System.out.println(parseExecutable(method));
        }

        //属性信息
        for (Field field : clazz.getFields()) {
            System.out.println(parseProp(field));
        }
    }

    private static String concat(int modifiers, String name, Class superClazz, Class[] interfaces) {
        String modifierInfo = parseModifiers(modifiers);

        String superClazzName = null;
        if (superClazz != null) {
            superClazzName = " extends " + superClazz.getSimpleName();
        }

        String interfaceInfo = concat(interfaces);

        return modifierInfo + " " + name + (StringUtils.isBlank(superClazzName) ? "" : superClazzName) + interfaceInfo;
    }

    private static void printFields(Class clazz, Object obj) throws IllegalAccessException {
        if (clazz == null) {
            return;
        }

        String clazzName = clazz.getSimpleName();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            System.out.format("%s.%s: %s.%n", clazzName, field.getName(), field.get(obj));
        }

        //print fields info recursively
        printFields(clazz.getSuperclass(), obj);
    }

    private static String concat(Class[] interfaces) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" implements ");

        int length = interfaces.length;
        for (int i = 0; i < length; i++) {
            stringBuilder.append(interfaces[i].getSimpleName());
            if (i != length - 1) {
                stringBuilder.append(",");
            }
        }

        return stringBuilder.toString();
    }

    //解析注解信息
    private static String parseAnnotation(Annotation annotation) {
        return annotation.toString();
    }


}

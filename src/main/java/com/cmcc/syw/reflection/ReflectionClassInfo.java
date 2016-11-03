package com.cmcc.syw.reflection;

import org.apache.commons.lang.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Formatter;

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
	
        //获取参数的类信息
        retriveType();

        //动态调用
        dynamicInvoke();

        //层级关系
        hierarchy();

        System.out.println("SuperClass of Object.class:" + Object.class.getSuperclass());

        Method supportedMethod = getSupportedMethod(ReflectionClass.class, "hashCode", null);
        System.out.println("SupportedMethod: " + supportedMethod.getName());
        System.out.println("DeclaringClass: " + supportedMethod.getDeclaringClass().getSimpleName());

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

    private static String serialize(Object obj, StringBuilder sb) {
        Class clazz = obj.getClass();

        sb.append("{");

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            try {
                Object fieldValue = field.get(obj);

                if (field.getType().isArray()) {

                } else if (field.getType().isPrimitive()) {
                    Formatter formatter = new Formatter(sb);
                    formatter.format("\"%s\":\"%s\",", field.getName(), field.get(obj));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        sb.append("}");
        return sb.toString();
    }

    private static void hierarchy() {
        Class<?> clazz = ReflectionClass.class;
        ReflectionClass rc = new ReflectionClass("world", 23);

        System.out.println("isInstance of rc: " + clazz.isInstance(rc));
        System.out.println("isInstance of obj: " + clazz.isInstance(new Object()));
        System.out.println("ReflectionClass.class isAssignableFrom Object.class: " + clazz.isAssignableFrom(Object.class));
        System.out.println("Object.class isAssignableFrom ReflectionClass.class: " + Object.class.isAssignableFrom(clazz));
    }

    private static void retriveType() {
        try {
            Class<?> clazz = ReflectionClass.class;

            Method method = clazz.getMethod("getIntProp");
            ReflectionClass rc = new ReflectionClass("hello", 32);
            System.out.println(method.getName() + ":" + method.invoke(rc));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void dynamicInvoke() {
        try {
            Class<?> clazz = ReflectionClass.class;

            ReflectionClass rc = new ReflectionClass("hello", 32);

            int value = 500;
            Method method = clazz.getMethod("setIntProp", int.class);
            method.invoke(rc, value);

            method = clazz.getMethod("getIntProp");
            value = (int) method.invoke(rc);
            System.out.println(method.getName() + ":" + value);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Method getSupportedMethod(Class<?> clazz, String name, Class<?>[] types) throws NoSuchMethodException {
        if (clazz == null) {
            throw new NoSuchMethodException();
        }

        try {
            return clazz.getDeclaredMethod(name, types);
        } catch (NoSuchMethodException e) {
            return getSupportedMethod(clazz.getSuperclass(), name, types);
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

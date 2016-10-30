package com.cmcc.syw.reflection;

import org.apache.commons.lang.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

/**
 * 通过反射获取类信息
 * <p/>
 * Created by sunyiwei on 2016/10/30.
 */
public class ReflectionClassInfo {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        Class<ReflectionClass> clazz = ReflectionClass.class;

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
            System.out.println(parseMethod(method));
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

    //解析属性信息
    private static String parseProp(Field field) {
        String modifierInfo = parseModifiers(field.getModifiers());
        String name = field.getName();
        String type = field.getType().getTypeName();

        return concat(name, type, modifierInfo);
    }

    //解析方法信息
    private static String parseMethod(Method method) {
        String modifiers = parseModifiers(method.getModifiers());
        String methodName = method.getName();

        StringBuilder paramInfo = new StringBuilder();
        paramInfo.append("(");

        Parameter[] params = method.getParameters();
        int length = method.getParameterCount();
        for (int i = 0; i < length; i++) {
            Parameter param = params[i];
            paramInfo.append(concat(param.getName(), param.getType().getTypeName(), parseModifiers(param.getModifiers())));
            if (i != length - 1) {
                paramInfo.append(",");
            }
        }

        paramInfo.append(")");

        return modifiers + methodName + paramInfo.toString();
    }

    private static String parseConstruct(Constructor cstc) {
        //名称
        String name = cstc.getName();

        //修饰符信息
        String modifiers = parseModifiers(cstc.getModifiers());

        return concat(name, "", modifiers);
    }

    private static String concat(String name, String type, String modifiers) {
        return modifiers + " " + type + " " + name;
    }

    private static String parseModifiers(int modifiers) {
        StringBuilder stringBuilder = new StringBuilder();
        if (Modifier.isFinal(modifiers)) {
            stringBuilder.append("final ");
        }

        if (Modifier.isPublic(modifiers)) {
            stringBuilder.append("public ");
        }

        if (Modifier.isPrivate(modifiers)) {
            stringBuilder.append("private ");
        }

        if (Modifier.isProtected(modifiers)) {
            stringBuilder.append("protected ");
        }

        if (Modifier.isStatic(modifiers)) {
            stringBuilder.append("static ");
        }

        return stringBuilder.toString();
    }
}

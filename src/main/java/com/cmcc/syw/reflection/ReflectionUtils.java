package com.cmcc.syw.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

/**
 * 反射工具类
 *
 * Created by sunyiwei on 2016/10/30.
 */
public class ReflectionUtils {
    //解析方法信息
    public static String parseExecutable(Executable executable) {
        String modifiers = parseModifiers(executable.getModifiers());
        String methodName = executable.getName();
        String returnTypeName = (executable instanceof Method) ? ((Method) executable).getReturnType().getName() : null;

        StringBuilder paramInfo = new StringBuilder();
        paramInfo.append("(");

        Parameter[] params = executable.getParameters();
        int length = executable.getParameterCount();
        for (int i = 0; i < length; i++) {
            Parameter param = params[i];
            paramInfo.append(concat(param.getName(), param.getType().getTypeName(), parseModifiers(param.getModifiers())));
            if (i != length - 1) {
                paramInfo.append(",");
            }
        }

        paramInfo.append(")");

        return modifiers + " " + returnTypeName + " " + methodName + paramInfo.toString();
    }

    private static String concat(String name, String type, String modifiers) {
        return modifiers + " " + type + " " + name;
    }

    //解析属性信息
    public static String parseProp(Field field) {
        String modifierInfo = parseModifiers(field.getModifiers());
        String name = field.getName();
        String type = field.getType().getTypeName();

        return concat(name, type, modifierInfo);
    }

    public static String parseConstruct(Constructor cstc) {
        //名称
        String name = cstc.getName();

        //修饰符信息
        String modifiers = parseModifiers(cstc.getModifiers());

        return concat(name, "", modifiers);
    }

    public static String parseModifiers(int modifiers) {
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

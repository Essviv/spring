package com.cmcc.syw.model;

import org.junit.Test;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

/**
 * 使用反射进行UT测试
 *
 * Created by sunyiwei on 2016/11/7.
 */
public class ReflectionTest {
    @Test
    public void test() throws Exception {
        test(Account.class);
        test(Authority.class);
        test(Car.class);
        test(Man.class);
        test(Model.class);
        test(Models.class);
        test(OrderReq.class);
        test(QueryReq.class);
        test(Person.class);
        test(Role.class);
        test(RoleAuthority.class);
        test(User.class);
        test(UserRole.class);
        test(ComplexModel.class);
    }

    @SuppressWarnings("unchecked")
    private void test(Class clazz) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        Field[] fs = clazz.getDeclaredFields();
        for (Field f : fs) {
            Object expectedValue = initValue(f.getType());
            if (expectedValue == null) {
                System.err.println("Cannot find no-arg constructor: " + f.getType());
                continue;
            }

            Object obj = clazz.newInstance();

            //set access
            f.setAccessible(true);

            //invoke setter
            String setMethodName = buildSetter(f.getName());
            Method setterMethod = clazz.getMethod(setMethodName, f.getType());
            setterMethod.invoke(obj, expectedValue);

            //invoke getter
            String getterMethodName = buildGetter(f.getName());
            Method getterMethod = clazz.getMethod(getterMethodName);
            Object returnedValue = getterMethod.invoke(obj);

            //assert
            assertEquals(returnedValue, expectedValue);
        }
    }

    private Object initValue(Class clazz) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor[] cstcs = clazz.getConstructors();
        for (Constructor cstc : cstcs) {
            if (cstc.getParameterCount() == 0) {
                return cstc.newInstance();
            }
        }

        if (clazz == int.class || clazz == Integer.class) {
            return 0;
        } else if (clazz == byte.class || clazz == Byte.class) {
            return (byte) 0;
        } else if (clazz == long.class || clazz == Long.class) {
            return 0L;
        } else if (clazz == String.class) {
            return "";
        } else if (clazz == float.class || clazz == Float.class) {
            return 1d;
        } else if (clazz == double.class || clazz == Double.class) {
            return 1d;
        } else if (clazz == boolean.class || clazz == Boolean.class) {
            return true;
        } else if (clazz.isArray()) {
            return Array.newInstance(clazz.getComponentType(), 1);
        } else if (clazz == List.class) {
            return new LinkedList<>();
        } else {
            return null;
        }
    }

    private String buildGetter(String fieldName) {
        return "get" + upperFirstLetter(fieldName);
    }

    private String buildSetter(String fieldName) {
        return "set" + upperFirstLetter(fieldName);
    }

    private String upperFirstLetter(String fieldName) {
        return fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }
}
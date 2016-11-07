package com.cmcc.syw.reflection;

import com.cmcc.syw.annotations.SerializerAnnotation;
import com.cmcc.syw.model.ComplexModel;
import com.cmcc.syw.model.Model;
import com.cmcc.syw.model.User;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

/**
 * 使用反射序列化对象成xml
 *
 * Created by sunyiwei on 2016/11/7.
 */
public class XmlSerializer {
    public static void main(String[] args) throws IllegalAccessException {
        new XmlSerializer().serialize(build());
    }

    private static ComplexModel build() {
        ComplexModel complexModel = new ComplexModel();
        complexModel.setName("dfdsnmn");
        complexModel.setModels(buildModelList());
        complexModel.setStrArray(buildStringArr());
        complexModel.setUser(buildUser());

        return complexModel;
    }

    private static User buildUser() {
        User user = new User();
        user.setName("fdsaf");
        user.setPassword("dfkldsajfl");

        return user;
    }

    private static String[] buildStringArr() {
        String[] stringArr = new String[]{"fdsaf", "fdkaslfj", "fkdlsjaf", "cmnxn"};
        return stringArr;
    }

    private static List<Model> buildModelList() {
        List<Model> list = new LinkedList<Model>();
        list.add(buildModel());
        list.add(buildModel());
        list.add(buildModel());
        list.add(buildModel());
        list.add(buildModel());
        list.add(buildModel());

        return list;
    }

    private static Model buildModel() {
        Model model = new Model();
        model.setName("fklalf");
        model.setAge(342);
        model.setGender("fkdjas");

        return model;
    }

    public void serialize(Object obj) throws IllegalAccessException {
        //add className as root
        Document document = DocumentHelper.createDocument();
        Class clazz = obj.getClass();
        String clzName = clazz.getSimpleName();

        SerializerAnnotation sa = (SerializerAnnotation) clazz.getAnnotation(SerializerAnnotation.class);
        if (sa != null) {
            clzName = sa.value();
        }


        Element root = document.addElement(clzName);
        serialize(obj, root);

        System.out.println(document.asXML());
    }

    private void serialize(Object obj, Element root) throws IllegalAccessException {
        Class clazz = obj.getClass();
        if (isPrimitiveType(clazz)) {
            root.addText(obj.toString());
            return;
        }

        Field[] fs = clazz.getDeclaredFields();
        for (Field f : fs) {
            String fieldName = f.getName();
            f.setAccessible(true);

            if (f.get(obj) != null) {
                Element elem = root.addElement(fieldName);
                Object value = f.get(obj);

                Class fieldType = f.getType();

                if (fieldType.isArray()) {
                    int length = Array.getLength(value);
                    for (int i = 0; i < length; i++) {
                        Element subElem = elem.addElement(fieldType.getComponentType().getSimpleName());
                        serialize(Array.get(value, i), subElem);
                    }
                } else if (List.class == fieldType) {
                    List valuelist = (List) value;
                    for (Object o : valuelist) {
                        Element subElem = elem.addElement(o.getClass().getSimpleName());
                        serialize(o, subElem);
                    }
                } else {
                    serialize(value, elem);
                }
            }
        }
    }

    private boolean isPrimitiveType(Class clazz) {
        return clazz.isPrimitive()
                || clazz == String.class
                || clazz == Byte.class
                || clazz == Short.class
                || clazz == Long.class
                || clazz == Float.class
                || clazz == Double.class
                || clazz == Integer.class
                || clazz == Void.class
                || clazz == Boolean.class
                || clazz == Character.class;
    }
}

package com.cmcc.syw.reflection;

import com.cmcc.syw.model.Model;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.dom.DOMDocumentType;

import java.lang.reflect.Field;

/**
 * 根据bean类型自动生成mapper文件
 *
 * Created by sunyiwei on 2016/11/7.
 */
public class BeanToMapper {
    public static void main(String[] args) {
        System.out.println(new BeanToMapper().toMapper(Model.class));
    }

    public String toMapper(Class clazz) {
        Document document = DocumentHelper.createDocument();

        //doctype
        document.setDocType(new DOMDocumentType("mapper", "-//mybatis.org//DTD Mapper 3.0//EN", "http://mybatis.org/dtd/mybatis-3-mapper.dtd"));

        //append root
        Element root = appendRoot(document, clazz);

        //append resultMap
        appendResultmap(clazz, root);

        //append sql
        appendSql(clazz, root);

        //append get
        appendGet(clazz, root);

        //append delete
        appendDelete(clazz, root);

        //append create
        appendCreate(clazz, root);

        return document.asXML();
    }

    private void appendGet(Class clazz, Element root) {

    }

    private void appendDelete(Class clazz, Element root) {

    }

    private void appendCreate(Class clazz, Element root) {
        Element create = root.addElement("insert");
        create.addAttribute("id", "insert");
        create.addAttribute("parameterType", clazz.getName());
        create.addAttribute("useGeneratedKeysr", "true");
        create.addAttribute("keyProperty", "");

        String insertText = buildInsertStatement();
        create.addText(insertText);
    }

    private String buildInsertStatement() {
        return "";
    }

    private Element appendRoot(Document document, Class clazz) {
        Element root = document.addElement("mapper");
        root.addAttribute("namespace", clazz.getName());

        return root;
    }

    private void appendSql(Class clazz, Element root) {
        Field[] fields = clazz.getDeclaredFields();
        Element element = root.addElement("sql");
        element.addAttribute("id", "Base_Column_List");

        StringBuilder sb = new StringBuilder();
        int length = fields.length;
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(fields[i].getName());
        }

        element.addText(sb.toString());
    }

    private void appendResultmap(Class clazz, Element root) {
        Element resultMapElem = root.addElement("resultMap");
        resultMapElem.addAttribute("id", "BaseResultMap");
        resultMapElem.addAttribute("type", clazz.getName());

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            Element resultElem = resultMapElem.addElement("result");
            resultElem.addAttribute("column", field.getName());
            resultElem.addAttribute("property", field.getName());
            resultElem.addAttribute("jdbcType", convert(field.getType()));
        }
    }

    private String convert(Class fieldType) {
        return "";
    }
}
